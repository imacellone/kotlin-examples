package com.italom.kotlin.examples.algorithms.ordering

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class MergeSortTest {

    @Test
    fun testMergeSort() {
        val a1 = intArrayOf(10, 20, 30)
        val a2 = intArrayOf(7, 14, 21, 28, 35, 42)
        val a3 = a1 + a2

        val firstResult = intArrayOf(7, 10, 14, 20, 21, 28, 30, 35, 42)
        val secondResult = intArrayOf(10, 7, 14, 20, 21, 30, 28, 35, 42)

        assertThat(doMergeSort(a1, a2)).isEqualTo(firstResult)
        assertThat(doMergeSort(a2, a1)).isEqualTo(firstResult)
        assertThat(doMergeSort(a3, 1, 3, 6)).isEqualTo(secondResult)
    }

}
