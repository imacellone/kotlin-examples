package com.italom.kotlin.examples.reflection

import kotlin.reflect.KProperty1
import kotlin.reflect.full.findAnnotation
import kotlin.reflect.full.memberProperties

fun Any?.toJSON() = serialize(this, isField = false)

private fun serialize(obj: Any?, isField: Boolean = true): String = buildString {
    append(
        when (obj) {
            null -> "null"
            is Number, is Boolean -> obj
            is String -> obj.toSerializedString(isField)
            is Iterable<*> -> obj.serialize()
            is Array<*> -> obj.serialize()
            is Map<*, *> -> obj.serialize()
            else -> serializeObject(obj)
        }
    )
}

private fun Any?.toSerializedString(withEnclosingDoubleQuotes: Boolean = true) =
    if (withEnclosingDoubleQuotes) "\"${toString().escape()}\"" else toString().escape()

private fun String.escape() = buildString {
    this@escape.forEach {
        append(
            when (it) {
                '\n' -> "\\n"
                '\t' -> "\\t"
                '\r' -> "\\r"
                '\u000C' -> "\\f"
                '\"' -> "\\\""
                '\b' -> "\\b"
                '\\' -> "\\\\"
                else -> it
            }
        )
    }
}

private fun Array<*>.serialize() = asIterable().serialize()

private fun Iterable<*>.serialize() = joinToString(separator = ",", prefix = "[", postfix = "]") { serialize(it) }

private fun Map<*, *>.serialize() =
    entries.asIterable().joinToString(separator = ",", prefix = "{", postfix = "}") { (it.key to it.value).serialize() }

// TODO: Add support for annotations: Custom Serializer
private fun serializeObject(obj: Any) =
    obj::class.memberProperties
        .filter { it.findAnnotation<JSONIgnore>() == null }
        .asIterable()
        .joinToString(separator = ",", prefix = "{", postfix = "}") { it.serialize(obj) }

// TODO: Check/add compatibility with Java
private fun KProperty1<*, *>.serialize(obj: Any) =
    ((findAnnotation<JSONCustomName>()?.name ?: name) to getter.call(obj)).serialize()

private fun Pair<*, *>.serialize(separator: String = ":") = buildString {
    append(first.toSerializedString())
    append(separator)
    append(serialize(second))
}
