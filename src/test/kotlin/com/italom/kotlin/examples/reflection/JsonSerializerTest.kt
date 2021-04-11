package com.italom.kotlin.examples.reflection

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

internal class JsonSerializerTest {

    @ParameterizedTest
    @MethodSource("getTestCaseMass")
    fun serialize(obj: Any, expectedJson: String) {
        assertThat(obj.toJSON()).isEqualTo(expectedJson)
    }

    companion object {
        @JvmStatic
        fun getTestCaseMass(): Stream<Arguments> {
            return Stream.of(

                Arguments.of(
                    object {
                        val name = "John Smith"
                    },
                    """{"name":"John Smith"}"""
                ),

                Arguments.of(
                    "John Smith",
                    "John Smith"
                )

            )
        }
    }
}


//data class Person(val name: String)