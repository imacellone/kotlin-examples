package com.italom.kotlin.examples.delegates.observer.action

fun <T> getLoggingAction() =
    { fieldName: String, oldValue: T, newValue: T -> println("Field: $fieldName has been updated from $oldValue to $newValue") }