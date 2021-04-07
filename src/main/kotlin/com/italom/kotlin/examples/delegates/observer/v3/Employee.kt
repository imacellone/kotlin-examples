package com.italom.kotlin.examples.delegates.observer.v3

import com.italom.kotlin.examples.delegates.observer.IEmployee
import kotlin.properties.Delegates
import kotlin.reflect.KProperty

class Employee(
    name: String,
    salary: Double
) : IEmployee {
    override var name by Delegates.observable(name, getLoggingAction())
    override var salary by Delegates.observable(salary, getLoggingAction())
}

fun <T> getLoggingAction() = { property: KProperty<*>, oldValue: T, newValue: T ->
    println("Field: ${property.name} has been updated from $oldValue to $newValue")
}
