package com.italom.kotlin.examples.algorithms.detection

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

internal class DetectionKtTest {

    private val fiat = Car("Fiat", 15999.0)
    private val ferrari = Car("Ferrari", 1000000.0)
    private val lamborghini = Car("Lamborghini", 900000.0)

    private val cars = listOf(
        ferrari,
        lamborghini,
        Car("VW", 16000.0),
        Car("Toyota", 20000.0),
        fiat
    )

    @Test
    fun findMinTest() {
        assertThat(minByOrNull(cars, Car::price)).isEqualTo(fiat)
        assertThat(minByOrNull(cars, Car::price, 0, 2)).isEqualTo(lamborghini)
        assertThat(minByOrNull(cars, Car::price, toIndexExclusive = 2)).isEqualTo(lamborghini)
        assertThat(minByOrNull(cars, Car::price, fromIndexInclusive = 3)).isEqualTo(fiat)
        assertThat(minByOrNull(emptyList<Car>(), Car::price)).isNull()
        assertThat(minByOrNull(listOf(ferrari), Car::price)).isEqualTo(ferrari)

        assertThrows<IndexOutOfBoundsException> {
            assertThat(minByOrNull(cars, Car::price, -1))
        }
        assertThrows<IndexOutOfBoundsException> {
            assertThat(minByOrNull(cars, Car::price, fromIndexInclusive = cars.size))
        }
        assertThrows<IndexOutOfBoundsException> {
            assertThat(minByOrNull(cars, Car::price, cars.size - 1, 0))
        }
        assertThrows<IndexOutOfBoundsException> {
            assertThat(minByOrNull(cars, Car::price, 0, cars.size + 1))
        }
    }
}

data class Car(val name: String, val price: Double)
