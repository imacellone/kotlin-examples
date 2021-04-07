package com.italom.kotlin.examples.delegates.observer.v1

class PropertyObserver<T, P>(
    initialValue: P,
    private val observer: ((propertyName: String, oldValue: P, newValue: P) -> Unit)? = null
) {
    private var value = initialValue

    fun getValue() = value

    fun setValue(propertyName: String, newValue: P) {
        val oldValue = value
        value = newValue
        observer?.invoke(propertyName, oldValue, newValue)
    }
}