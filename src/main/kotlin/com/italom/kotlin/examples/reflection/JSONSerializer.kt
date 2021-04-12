package com.italom.kotlin.examples.reflection

import kotlin.reflect.full.declaredMemberProperties

fun Any?.toJSON() = serialize(this, isField = false)

// TODO: Add support for collections and maps.
private fun serialize(obj: Any?, isField: Boolean = true): String = buildString {
    append(
        when (obj) {
            null -> "null"
            is Number, is Boolean -> obj
            is String -> obj.toEnclosedString(isField)
            is Array<*> -> obj.serialize()
            else -> serializeObject(obj)
        }
    )
}

// TODO: Add support for annotations: Ignore Property and Custom Name.
private fun serializeObject(obj: Any): String = buildString {
    append("{")

    val declaredMemberProperties = obj::class.java.kotlin .declaredMemberProperties
    declaredMemberProperties
        .forEachIndexed {index, property ->
            append(property.name.toEnclosedString())
            append(":")
            append(serialize(property.getter.call(obj)))
            if (index < declaredMemberProperties.size - 1 ) append(",")
        }

    append("}")
}

private fun Any?.toEnclosedString(needsEnclosing: Boolean = true, enclosingString: String = "\"") =
    if (!needsEnclosing) this.toString() else "$enclosingString$this$enclosingString"

private fun Array<*>.serialize() = buildString {
    append("[")
    this@serialize.forEachIndexed { index, element ->
        append(serialize(element))
        if (index < this@serialize.size - 1) append(",")
    }
    append("]")
}
