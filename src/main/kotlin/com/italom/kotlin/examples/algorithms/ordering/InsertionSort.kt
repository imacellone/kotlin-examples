package com.italom.kotlin.examples.algorithms.ordering

fun <T, R : Comparable<R>> doInsertionSort(elements: Array<T>, selector: (T) -> R) {
    (1 until elements.size).forEach { currentIndex ->
        var analyzedIndex = currentIndex
        while (analyzedIndex > 0 && selector(elements[analyzedIndex]) < selector(elements[analyzedIndex - 1])) {
            swap(elements, analyzedIndex, analyzedIndex - 1)
            analyzedIndex--
        }
    }
}

private fun <T> swap(elements: Array<T>, index1: Int, index2: Int) {
    val element1 = elements[index1]
    elements[index1] = elements[index2]
    elements[index2] = element1
}
