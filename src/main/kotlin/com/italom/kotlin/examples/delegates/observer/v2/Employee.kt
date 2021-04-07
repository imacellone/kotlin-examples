package com.italom.kotlin.examples.delegates.observer.v2

class Employee(
    name: String,
    salary: Double
) {
    var name by PropertyObserver<Employee, String>(initialValue = name, observer = getLoggingAction())
    var salary by PropertyObserver<Employee, Double>(initialValue = salary, observer = getLoggingAction())
}