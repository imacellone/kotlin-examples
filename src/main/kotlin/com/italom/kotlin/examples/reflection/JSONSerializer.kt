package com.italom.kotlin.examples.reflection

import kotlin.reflect.full.declaredMemberProperties

fun Any?.toJSON() = serialize(this, isField = false)

// TODO: Add support for arrays, collections and maps.
private fun serialize(obj: Any?, isField: Boolean = true): String = buildString {
    append(
        when (obj) {
            null -> "null"
            is Number, is Boolean -> obj
            is String -> obj.toEnclosedString(isField)
            else -> serializeObject(obj)
        }
    )
}

// TODO: Add support for annotations: Ignore Property and Custom Name.
private fun serializeObject(obj: Any): String = buildString {
    append("{")

    obj::class.java.kotlin
        .declaredMemberProperties
        .forEach { property ->
            append(property.name.toEnclosedString())
            append(":")
            append(serialize(property.getter.call(obj)))
        }

    append("}")
}

private fun Any?.toEnclosedString(needsEnclosing: Boolean = true, enclosingString: String = "\"") =
    if (!needsEnclosing) this.toString() else """$enclosingString$this$enclosingString"""
