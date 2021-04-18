package com.italom.kotlin.examples.algorithms.ordering

import com.italom.kotlin.examples.algorithms.detection.indexOfMinByOrNull

fun <T, R : Comparable<R>> doSelectionSort(elements: List<T>, selector: (T) -> R): List<T> {
    val mutableElementList = elements.toMutableList()
    for (i in 0 until mutableElementList.size - 1) {
        val currentElement = mutableElementList[i]
        val minIndex = indexOfMinByOrNull(mutableElementList, selector, fromIndexInclusive = i)
        mutableElementList[i] = mutableElementList[minIndex]
        mutableElementList[minIndex] = currentElement
    }
    return mutableElementList.toList()
}