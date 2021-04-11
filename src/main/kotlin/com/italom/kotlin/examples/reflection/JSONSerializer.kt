package com.italom.kotlin.examples.reflection

import java.math.BigDecimal
import java.math.BigInteger
import kotlin.reflect.full.declaredMemberProperties

fun Any?.toJSON() = serialize(this, isField = false)

private fun serialize(obj: Any?, isField: Boolean = true): String = buildString {
    append(
        when (obj) {
            null -> "null"
            isBasicType() -> obj
            is String -> obj.toEnclosedString(isField)
            else -> serializeObject(obj)
        }
    )
}

private fun Any.isBasicType() =
    when (this) {
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
