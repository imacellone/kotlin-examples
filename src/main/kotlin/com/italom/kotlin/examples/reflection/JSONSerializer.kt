package com.italom.kotlin.examples.reflection

import kotlin.reflect.KProperty1
import kotlin.reflect.full.memberProperties

fun Any?.toJSON() = serialize(this, isField = false)

private fun serialize(obj: Any?, isField: Boolean = true): String = buildString {
    append(
        when (obj) {
            null -> "null"
            is Number, is Boolean -> obj
            is String -> obj.toEnclosedString(isField)
            is Iterable<*> -> obj.serialize()
            is Array<*> -> obj.serialize()
            is Map<*, *> -> obj.serialize()
            else -> serializeObject(obj)
        }
    )
}

// TODO: Escape special characters
private fun Any?.toEnclosedString(needsEnclosing: Boolean = true, enclosingString: String = "\"") =
    if (!needsEnclosing) toString() else "$enclosingString$this$enclosingString"

private fun Array<*>.serialize() = asIterable().serialize()

private fun Iterable<*>.serialize() = joinToString(separator = ",", prefix = "[", postfix = "]") { serialize(it) }

private fun Map<*, *>.serialize() =
    entries.asIterable().joinToString(separator = ",", prefix = "{", postfix = "}") { (it.key to it.value).serialize() }

private fun serializeObject(obj: Any) =
    obj::class.memberProperties.asIterable()
        .joinToString(separator = ",", prefix = "{", postfix = "}") { it.serialize(obj) }

// TODO: Add support for annotations: Ignore Property, Custom Name and Custom Serializer
private fun KProperty1<*, *>.serialize(obj: Any) = (name to getter.call(obj)).serialize()

private fun Pair<*, *>.serialize(separator: String = ":") = buildString {
    append(first.toEnclosedString())
    append(separator)
    append(serialize(second))
}
