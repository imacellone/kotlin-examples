package com.italom.kotlin.examples.reflection

import kotlin.annotation.AnnotationTarget.FIELD
import kotlin.annotation.AnnotationTarget.PROPERTY
import kotlin.reflect.KClass

@Target(PROPERTY, FIELD)
annotation class JSONIgnore

@Target(PROPERTY, FIELD)
annotation class JSONCustomName(val value: String)

@Target(PROPERTY, FIELD)
annotation class JSONCustomSerializer(val value: KClass<out CustomSerializer<*>>)