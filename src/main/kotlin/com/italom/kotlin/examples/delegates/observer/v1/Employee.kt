package com.italom.kotlin.examples.delegates.observer.v1

class Employee(
    name: String,
    salary: Double
) {
    private val _name = PropertyObserver<Employee, String>(name, getLoggingAction())

    var name: String
        get() = _name.getValue()
        set(newValue) = _name.setValue(propertyName = "name", newValue)

    private val _salary = PropertyObserver<Employee, Double>(salary, getLoggingAction())

    var salary: Double
        get() = _salary.getValue()
        set(newValue) = _salary.setValue(propertyName = "salary", newValue)

}