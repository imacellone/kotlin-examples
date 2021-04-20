package com.italom.kotlin.examples.algorithms.ordering

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class MergeSortTest {

    @Test
    fun test() {
        val originalArray = intArrayOf(13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1)
        val orderedArray = intArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13)
        mergeSort(originalArray)
        assertThat((originalArray)).isEqualTo(orderedArray)
    }

}
