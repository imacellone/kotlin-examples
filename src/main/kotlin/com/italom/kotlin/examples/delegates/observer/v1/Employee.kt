package com.italom.kotlin.examples.delegates.observer.v1

import com.italom.kotlin.examples.delegates.observer.IEmployee

class Employee(
    name: String,
    salary: Double
) : IEmployee {
    private val _name = PropertyObserver<Employee, String>(name, getLoggingAction())

    override var name: String
        get() = _name.getValue()
        set(newValue) = _name.setValue(propertyName = "name", newValue)

    private val _salary = PropertyObserver<Employee, Double>(salary, getLoggingAction())

    override var salary: Double
        get() = _salary.getValue()
        set(newValue) = _salary.setValue(propertyName = "salary", newValue)

}