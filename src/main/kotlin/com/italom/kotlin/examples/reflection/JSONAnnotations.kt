package com.italom.kotlin.examples.reflection

import kotlin.annotation.AnnotationTarget.PROPERTY

@Target(PROPERTY)
annotation class JSONIgnore

@Target(PROPERTY)
annotation class JSONCustomName(val name: String)