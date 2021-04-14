package com.italom.kotlin.examples.reflection

interface CustomSerializer<T> {
    fun serialize(element: T) : Any?
}
