package com.italom.kotlin.examples.delegates.lazy.v2

class Person(
    val name: String,
    private val favoriteBandsDataSource: List<String>
) {
    val favoriteBands: List<String> by lazy(::favoriteBandsDataSource)
}