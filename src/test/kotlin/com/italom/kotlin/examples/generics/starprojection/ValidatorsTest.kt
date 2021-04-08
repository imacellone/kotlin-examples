package com.italom.kotlin.examples.generics.starprojection

import com.italom.kotlin.examples.generics.starprojection.Validators.validate
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS
import org.junit.jupiter.api.assertThrows
import kotlin.test.assertFalse
import kotlin.test.assertTrue

@TestInstance(PER_CLASS)
class ValidatorsTest {

    @BeforeAll
    fun setUp() {
        Validators[Double::class] = DoubleValidator()
        Validators[String::class] = StringValidator()
    }

    @Test
    fun `Validator should pass when string is not empty`() {
        assertTrue(validate("Not an empty string!"))
    }

    @Test
    fun `Validator should pass when double is greater than 0`() {
        assertTrue(validate(0.1))
        assertTrue(validate(1.0))
    }

    @Test
    fun `Validator should not pass when double is equals or lower than 0`() {
        assertFalse(validate(0.0))
        assertFalse(validate(-1.0))
    }

    @Test
    fun `Validator should not pass empty or blank String`() {
        assertFalse(validate(""))
        assertFalse(validate(" "))
    }

    @Test
    fun `Validator should throw an exception when trying to validate a type not registered`() {
        assertThrows<IllegalArgumentException> {
            validate(Any())
        }
        assertThrows<IllegalArgumentException> {
            validate(12)
        }

        assertThrows<IllegalArgumentException> {
            validate('a')
        }
    }

}
