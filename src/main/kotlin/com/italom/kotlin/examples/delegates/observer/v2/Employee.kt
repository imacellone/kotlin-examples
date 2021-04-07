package com.italom.kotlin.examples.delegates.observer.v2

import com.italom.kotlin.examples.delegates.observer.IEmployee

class Employee(
    name: String,
    salary: Double
) : IEmployee {
    override var name by PropertyObserver<Employee, String>(initialValue = name, observer = getLoggingAction())
    override var salary by PropertyObserver<Employee, Double>(initialValue = salary, observer = getLoggingAction())
}