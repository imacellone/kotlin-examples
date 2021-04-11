package com.italom.kotlin.examples.reflection

import kotlin.reflect.full.declaredMemberProperties

// TODO Decide whether class or object
class JsonSerializer {

    fun <T : Any> serialize(obj: T) = buildString {
        append("{")
        val clazz = obj::class.java.kotlin
        val properties = clazz.declaredMemberProperties
        properties.forEach { property ->
            val name = property.name
            val value = property.getter.call(obj)
            append("\"$name\"")
            append(":")
            append(serializeValue(value))
        }
        append("}")
    }

    private fun serializeValue(value: Any?) =
        when (value) {
            null -> value.toString()
            is String -> """"$value""""
            else -> "{}"
        }

}