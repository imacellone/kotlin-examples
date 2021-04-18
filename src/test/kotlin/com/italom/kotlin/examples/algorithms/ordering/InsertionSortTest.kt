package com.italom.kotlin.examples.algorithms.ordering

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class InsertionSortTest {

    private val ferrari = Car("Ferrari", 1000000.0)
    private val lamborghini = Car("Lamborghini", 900000.0)
    private val toyota = Car("Toyota", 20000.0)
    private val vw = Car("VW", 16000.0)
    private val fiat = Car("Fiat", 15999.0)

    private val orderedCars = arrayOf(
        fiat, vw, toyota, lamborghini, ferrari
    )

    @Test
    fun testInsertionSort() {
        var unorderedCars = arrayOf(
            toyota,
            vw,
            fiat,
            ferrari,
            lamborghini
        )
        doInsertionSort(unorderedCars, Car::price)
        assertThat(unorderedCars).isEqualTo(orderedCars)

        unorderedCars = arrayOf(fiat)
        doInsertionSort(unorderedCars, Car::price)
        assertThat(unorderedCars).isEqualTo(arrayOf(fiat))

        unorderedCars = emptyArray()
        doInsertionSort(unorderedCars, Car::price)
        assertThat(unorderedCars).isEqualTo(emptyArray<Car>())
    }
}

data class Car(val name: String, val price: Double)
