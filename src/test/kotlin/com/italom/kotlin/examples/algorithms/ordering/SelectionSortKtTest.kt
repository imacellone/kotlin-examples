package com.italom.kotlin.examples.algorithms.ordering

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class SelectionSortKtTest {

    private val ferrari = Car("Ferrari", 1000000.0)
    private val lamborghini = Car("Lamborghini", 900000.0)
    private val toyota = Car("Toyota", 20000.0)
    private val vw = Car("VW", 16000.0)
    private val fiat = Car("Fiat", 15999.0)

    private val unorderedCars = listOf(
        toyota,
        vw,
        fiat,
        ferrari,
        lamborghini
    )

    private val orderedCars = listOf(
        fiat, vw, toyota, lamborghini, ferrari
    )

    @Test
    fun testSelectionSort() {
        assertThat(doSelectionSort(unorderedCars, Car::price)).containsExactly(*orderedCars.toTypedArray())
    }

    data class Car(val name: String, val price: Double)
}