package com.italom.kotlin.examples.reflection

import kotlin.annotation.AnnotationTarget.PROPERTY
import kotlin.reflect.KClass

@Target(PROPERTY)
annotation class JSONIgnore

@Target(PROPERTY)
annotation class JSONCustomName(val name: String)

@Target(PROPERTY)
annotation class JSONCustomSerializer(val serializer: KClass<out CustomSerializer<*>>)