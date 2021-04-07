package com.italom.kotlin.examples.delegates.lazy.v1

import org.assertj.core.api.SoftAssertions.assertSoftly
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.fail
import kotlin.reflect.jvm.isAccessible

internal class PersonTest {

    private val favoriteBandsBackingPropertyName = "_favoriteBands"

    private val favoriteBandsDataSource = listOf(
        "Iron Maiden",
        "Black Sabbath",
        "AC/DC",
        "Led Zeppelin"
    )

    @Test
    fun `favorite bands should be retrieved lazily`() {
        val person = Person(
            name = "John Smith",
            favoriteBandsDataSource
        )

        getFavoriteBandsBackingFieldValue(person)?.let {
            fail("$favoriteBandsBackingPropertyName should be null at this point")
        }

        val favoriteBands = person.favoriteBands
        val favoriteBandsBackingFieldValue = getFavoriteBandsBackingFieldValue(person)
        assertSoftly { softly ->
            softly.assertThat(favoriteBandsBackingFieldValue).containsExactlyInAnyOrder(*favoriteBandsDataSource.toTypedArray())
            softly.assertThat(favoriteBandsBackingFieldValue).isEqualTo(favoriteBands)
        }

    }

    @Suppress("UNCHECKED_CAST")
    private fun getFavoriteBandsBackingFieldValue(person: Person) =
        Person::class.members
            .find { it.name == favoriteBandsBackingPropertyName }
            ?.let { member ->
                member.isAccessible = true
                member.call(person) as List<String>?
            }
}