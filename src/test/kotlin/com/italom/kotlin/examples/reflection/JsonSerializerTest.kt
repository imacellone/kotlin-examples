package com.italom.kotlin.examples.reflection

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
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
                    """D:\Dir1\Dir2""",
                    "D:\\\\Dir1\\\\Dir2"
                ),

                Arguments.of(
                    "John\nSmith",
                    "John\\nSmith"
                ),

                Arguments.of(
                    "John\tSmith",
                    "John\\tSmith"
                ),

                Arguments.of(
                    "John\rSmith",
                    "John\\rSmith"
                ),

                Arguments.of(
                    "John\u000CSmith",
                    "John\\fSmith"
                ),

                Arguments.of(
                    """name: "John Smith"""",
                    """name: \"John Smith\""""
                ),

                Arguments.of(
                    "John\bSmith",
                    "John\\bSmith"
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
                        val name = "John\tSmith"
                    },
                    """{"name":"John\tSmith"}"""
                ),

                Arguments.of(
                    null,
                    "null"
                ),

                Arguments.of(
                    arrayOf("John\nSmith"),
                    """["John\nSmith"]"""
                ),

                Arguments.of(
                    arrayOf("John", "Smith"),
                    """["John","Smith"]"""
                ),

                Arguments.of(
                    arrayOf(1, 2, 3),
                    "[1,2,3]"
                ),

                Arguments.of(
                    arrayOf(1.1, 2.2, 3.3),
                    "[1.1,2.2,3.3]"
                ),

                Arguments.of(
                    emptyArray<Any>(),
                    "[]"
                ),

                Arguments.of(
                    listOf("John\rSmith"),
                    """["John\rSmith"]"""
                ),

                Arguments.of(
                    setOf("John", "Smith"),
                    """["John","Smith"]"""
                ),

                Arguments.of(
                    listOf(1, 2, 3),
                    "[1,2,3]"
                ),

                Arguments.of(
                    setOf(1.1, 2.2, 3.3),
                    "[1.1,2.2,3.3]"
                ),

                Arguments.of(
                    emptyList<Any>(),
                    "[]"
                ),

                Arguments.of(
                    arrayOf(object {
                        val name = "John Smith"
                        val age = 80
                        val salary = 1_000.0
                    }, object {}),
                    """[{"age":80,"name":"John Smith","salary":1000.0},{}]"""
                ),

                Arguments.of(
                    mapOf("name" to "John", "last\tName" to "Smith", "fullName" to "John\tSmith"),
                    """{"name":"John","last\tName":"Smith","fullName":"John\tSmith"}"""
                ),

                Arguments.of(
                    mapOf("john" to object {
                        val name = "John Smith"
                        val age = 80
                    }),
                    """{"john":{"age":80,"name":"John Smith"}}"""
                ),

                Arguments.of(
                    object {
                        val name = "John"

                        @JSONCustomName(value = "LASTNAME")
                        val lastName = "Smith"

                        @JSONCustomSerializer(value = CustomSerializerObject::class)
                        val employeeId = "abcde"

                        @JSONIgnore
                        val salary = 1000.0
                    },
                    """{"employeeId":"ABCDE","LASTNAME":"Smith","name":"John"}"""
                ),

                Arguments.of(
                    object {
                        @JSONCustomSerializer(value = CustomSerializerClass::class)
                        val employeeId = "abcde"
                    },
                    """{"employeeId":"ABCDE"}"""
                )
            )
        }
    }
}

object CustomSerializerObject : CustomSerializer<String> {
    override fun serialize(element: String) = element.toUpperCase()
}

class CustomSerializerClass : CustomSerializer<String> {
    override fun serialize(element: String) = element.toUpperCase()
}