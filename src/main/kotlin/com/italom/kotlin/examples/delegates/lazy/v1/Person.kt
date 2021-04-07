package com.italom.kotlin.examples.delegates.lazy.v1

class Person(
    val name: String,
    private val favoriteBandsDataSource: List<String>
) {
    private var _favoriteBands: List<String>? = null

    val favoriteBands: List<String>
        get() {
            if (_favoriteBands == null) _favoriteBands = favoriteBandsDataSource
            return _favoriteBands?.toList() ?: emptyList()
        }

}