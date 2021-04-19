package com.italom.kotlin.examples.algorithms.ordering

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class MergeSortTest {

    @Test
    fun testMergeSort() {
        val a1 = intArrayOf(10, 20, 30)
        val a2 = intArrayOf(7, 14, 21, 28, 35, 42)
        val expectedMergeSortResult = intArrayOf(7, 10, 14, 20, 21, 28, 30, 35, 42)
        assertThat(doMergeSort(a1, a2)).isEqualTo(expectedMergeSortResult)
        assertThat(doMergeSort(a2, a1)).isEqualTo(expectedMergeSortResult)
    }

}
