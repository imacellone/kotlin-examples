package com.italom.kotlin.examples.algorithms.ordering

fun doMergeSort(a: IntArray, b: IntArray) : IntArray {
    val result = IntArray(a.size + b.size)

    var indexResult = 0
    var indexA = 0
    var indexB = 0

    while (indexA < a.size && indexB < b.size)
        result[indexResult++] = if (a[indexA] < b[indexB]) a[indexA++] else b[indexB++]

    while (indexA < a.size)
        result[indexResult++] = a[indexA++]

    while (indexB < b.size)
        result[indexResult++] = b[indexB++]

    return result
}