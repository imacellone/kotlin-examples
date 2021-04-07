package com.italom.kotlin.examples.delegates.observer

import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import java.io.ByteArrayOutputStream
import java.io.PrintStream
import java.util.stream.Stream
import com.italom.kotlin.examples.delegates.observer.v1.Employee as EmployeeV1
import com.italom.kotlin.examples.delegates.observer.v2.Employee as EmployeeV2
import com.italom.kotlin.examples.delegates.observer.v3.Employee as EmployeeV3

@TestInstance(PER_CLASS)
internal class EmployeeIT {

    private val standardOut = System.out
    private lateinit var outputCaptor: ByteArrayOutputStream
    private val newName = "John Smith"
    private val newSalary = 2_000.0

    @BeforeEach
    fun setUp() {
        outputCaptor = ByteArrayOutputStream()
        System.setOut(PrintStream(outputCaptor))
    }

    @AfterAll
    fun tearDown() = System.setOut(standardOut)

    @ParameterizedTest
    @MethodSource("getEmployeeTestCaseMass")
    fun `changing Employee's initial properties should fire their observing action`(employee: IEmployee) {
        employee.apply {
            name = newName
            salary = newSalary
        }.run {
            assertEquals(newName, name)
            assertEquals(newSalary, salary)
        }

        assertEquals(
            """
            Field: name has been updated from John to $newName
            Field: salary has been updated from 1000.0 to $newSalary
            """.trimIndent(),

            outputCaptor.toString().trim()
        )
    }

    companion object {
        private const val initialName = "John"
        private const val initialSalary = 1_000.0

        @JvmStatic
        fun getEmployeeTestCaseMass(): Stream<IEmployee> =
            Stream.of(
                EmployeeV1(initialName, initialSalary),
                EmployeeV2(initialName, initialSalary),
                EmployeeV3(initialName, initialSalary)
            )
    }
}
