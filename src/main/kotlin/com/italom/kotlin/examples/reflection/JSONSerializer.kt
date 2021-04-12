package com.italom.kotlin.examples.reflection

import kotlin.reflect.full.memberProperties

fun Any?.toJSON() = serialize(this, isField = false)

private fun serialize(obj: Any?, isField: Boolean = true): String = buildString {
    append(
        when (obj) {
            null -> "null"
            is Number, is Boolean -> obj
            is String -> obj.toEnclosedString(isField)
            is Collection<*> -> obj.serialize()
            is Array<*> -> obj.serialize()
            is Map<*, *> -> obj.serialize()
            else -> serializeObject(obj)
        }
    )
}

private fun Any?.toEnclosedString(needsEnclosing: Boolean = true, enclosingString: String = "\"") =
    if (!needsEnclosing) this.toString() else "$enclosingString$this$enclosingString"

private fun Collection<*>.serialize() = this.toTypedArray().serialize()

private fun Array<*>.serialize() = buildString {
    append("[")
    this@serialize.forEachIndexed { index, element ->
        append(serialize(element))
        if (index < this@serialize.size - 1) append(",")
    }
    append("]")
}

private fun Map<*, *>.serialize() = buildString {
    append("{")
    var index = 0
    this@serialize.forEach { (key, value) ->
        append(key.toEnclosedString())
        append(":")
        append(serialize(value))
        if (index < this@serialize.size - 1) append(",")
        index++
    }
    append("}")
}

// TODO: Add support for annotations: Ignore Property and Custom Name.
private fun serializeObject(obj: Any): String = buildString {
    append("{")
    val memberProperties = obj::class.memberProperties
    memberProperties
        .forEachIndexed { index, property ->
            append(property.name.toEnclosedString())
            append(":")
            append(serialize(property.getter.call(obj)))
            if (index < memberProperties.size - 1) append(",")
        }
    append("}")
}
