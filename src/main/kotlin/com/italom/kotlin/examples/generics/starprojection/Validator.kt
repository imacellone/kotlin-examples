package com.italom.kotlin.examples.generics.starprojection

import kotlin.reflect.KClass

object Validators {
    private val validators = mutableMapOf<KClass<*>, IValidator<*>>()

    operator fun <T : Any> set(kClass: KClass<T>, validator: IValidator<T>) {
        validators[kClass] = validator
    }

    @Suppress("UNCHECKED_CAST")
    fun <T : Any> validate(value: T) =
        (validators[value::class] as? IValidator<T>
            ?: throw IllegalArgumentException("No validator for ${value::class} found."))
            .validate(value)
}

class StringValidator : IValidator<String> {
    override fun validate(arg: String): Boolean = arg.isNotBlank()
}

class DoubleValidator : IValidator<Double> {
    override fun validate(arg: Double) = arg > 0
}

interface IValidator<in T> {
    fun validate(arg: T): Boolean
}
