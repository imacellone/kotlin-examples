package com.italom.kotlin.examples.reflection

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.math.BigDecimal
import java.math.BigInteger
import java.util.stream.Stream

internal class JsonSerializerTest {

    @ParameterizedTest
    @MethodSource("getTestCaseMass")
    fun serialize(obj: Any?, expectedJson: String) {
        assertThat(obj.toJSON()).isEqualTo(expectedJson)
    }

    companion object {
        @JvmStatic
        fun getTestCaseMass(): Stream<Arguments> {
            return Stream.of(

                Arguments.of(
                    10.toByte(),
                    "10"
                ),

                Arguments.of(
                    10.toShort(),
                    "10"
                ),

                Arguments.of(
                    10,
                    "10"
                ),

                Arguments.of(
                    10.toLong(),
                    "10"
                ),

                Arguments.of(
                    10.1F,
                    "10.1"
                ),

                Arguments.of(
                    10.1,
                    "10.1"
                ),

                Arguments.of(
                    true,
                    "true"
                ),

                Arguments.of(
                    false,
                    "false"
                ),

                Arguments.of(
                    "John Smith",
                    "John Smith"
                ),

                Arguments.of(
                    BigInteger.TEN,
                    "10"
                ),

                Arguments.of(
                    10.0.toBigDecimal(),
                    "10.0"
                ),

                Arguments.of(
                    object {
                        val name = "John Smith"
                    },
                    """{"name":"John Smith"}"""
                ),

                Arguments.of(
                    null,
                    "null"
                )
            )
        }
    }
}


//data class Person(val name: String)