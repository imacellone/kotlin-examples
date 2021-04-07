package com.italom.kotlin.examples.delegates.observer.v2

import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS
import java.io.ByteArrayOutputStream
import java.io.PrintStream

@TestInstance(PER_CLASS)
class EmployeeIT {

    private val standardOut = System.out
    private lateinit var outputCaptor: ByteArrayOutputStream

    @BeforeEach
    fun setUp() {
        outputCaptor = ByteArrayOutputStream()
        System.setOut(PrintStream(outputCaptor))
    }

    @AfterAll
    fun tearDown() = System.setOut(standardOut)
    @Test
    fun `changing Employee's initial properties should fire their observing action`() {
        val newName = "John Smith"
        val newSalary = 2_000.0

        Employee("John", 1_000.0)
            .apply {
                name = newName
                salary = newSalary
            }.run {
                assertEquals(newName, name)
                assertEquals(newSalary, salary)
            }

        assertSystemOutEquals(
            expected = """
            Field: name has been updated from John to $newName
            Field: salary has been updated from 1000.0 to $newSalary
            """.trimIndent()
        )
    }

    private fun assertSystemOutEquals(expected: String) = assertEquals(expected, outputCaptor.toString().trim())

}