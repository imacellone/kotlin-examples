package com.italom.kotlin.examples.reflection

import java.math.BigDecimal
import java.math.BigInteger
import kotlin.reflect.full.declaredMemberProperties

// TODO Decide whether class or object
class JsonSerializer {

    fun toJSON(obj: Any?) = serialize(obj, isField = false)

    private fun serialize(obj: Any?, isField: Boolean = true): String = buildString {
        append(
            when (obj) {
                null -> "null"
                isBasicType(obj) -> obj
                is String -> obj.toEnclosedString(isField)
                else -> serializeObject(obj)
            }
        )
    }

    private fun isBasicType(obj: Any) =
        when (obj) {
            is Byte,
            is Short,
            is Int,
            is Long,
            is Float,
            is Double,
            is BigInteger,
            is BigDecimal,
            is Boolean -> true
            else -> false
        }

    private fun serializeObject(obj: Any): String = buildString {
        append("{")
        val clazz = obj::class.java.kotlin
        val properties = clazz.declaredMemberProperties
        properties.forEach { property ->
            val name = property.name
            val value = property.getter.call(obj)
            append(name.toEnclosedString())
            append(":")
            append(serialize(value))
        }
        append("}")
    }

    private fun String.toEnclosedString(enclose: Boolean = true, enclosure: String = "\""): String {
        if (!enclose) return this
        return """$enclosure$this$enclosure"""
    }

}