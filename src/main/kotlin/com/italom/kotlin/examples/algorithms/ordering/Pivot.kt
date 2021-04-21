package com.italom.kotlin.examples.algorithms.ordering

fun quickSort(elements: IntArray) = quickSort(elements, 0, elements.size)

fun quickSort(elements: IntArray, start: Int, end: Int) {
    val size = end - start
    if (size <= 1) return
    val pivotPosition = breakAtPivot(elements, start, end)
    quickSort(elements, start, pivotPosition)
    quickSort(elements, pivotPosition + 1, end)
}

fun breakAtPivot(elements: IntArray, start: Int, end: Int): Int {
    var pivotFinalPosition = start

    for (i in start until end - 1)
        if (elements[i] < elements[end - 1]) {
            swap(elements, i, pivotFinalPosition)
            pivotFinalPosition++
        }

    swap(elements, end - 1, pivotFinalPosition)
    return pivotFinalPosition
}

private fun swap(elements: IntArray, index1: Int, index2: Int) {
    val element1 = elements[index1]
    elements[index1] = elements[index2]
    elements[index2] = element1
}