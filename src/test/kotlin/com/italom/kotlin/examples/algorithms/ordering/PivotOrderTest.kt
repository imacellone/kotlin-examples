package com.italom.kotlin.examples.algorithms.ordering

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PivotOrderTest {

    @Test
    fun testQuickSort() {
        val array = intArrayOf(10, 9, 8, 7, 6, 5, 4, 3, 2, 1)
        val orderedArray = intArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
        quickSort(array)
        assertThat(array).isEqualTo(orderedArray)
    }

    @Test
    fun testPivot() {
        val array = intArrayOf(6, 2, 7, 5, 12, 0, 20, 4)
        val expectedArray = intArrayOf(2, 0, 4, 5, 12, 6, 20, 7)
        val expectedAmountOfLowerElements = 2

        val actualAmountOfLowerElements = breakAtPivot(array, 0, array.size)

        assertThat(actualAmountOfLowerElements).isEqualTo(expectedAmountOfLowerElements)
        assertThat(array).isEqualTo(expectedArray)
    }

}