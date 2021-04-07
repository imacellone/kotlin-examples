package com.italom.kotlin.examples.delegates.observer.v2

import kotlin.reflect.KProperty

class PropertyObserver<T, P>(
    initialValue: P,
    private val observer: ((property: KProperty<*>, oldValue: P, newValue: P) -> Unit)? = null
) {
    private var value = initialValue

    operator fun getValue(instance: T, property: KProperty<*>) = value

    operator fun setValue(instance: T, property: KProperty<*>, newValue: P) {
        val oldValue = value
        value = newValue
        observer?.invoke(property, oldValue, newValue)
    }
}

fun <T> getLoggingAction() = { property: KProperty<*>, oldValue: T, newValue: T ->
    println("Field: ${property.name} has been updated from $oldValue to $newValue")
}
