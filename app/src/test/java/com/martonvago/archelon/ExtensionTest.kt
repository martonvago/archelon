package com.martonvago.archelon

import com.google.common.truth.Truth.assertThat
import com.martonvago.archelon.entity.Displayable
import com.martonvago.archelon.entity.enumValuesAsDisplayable
import com.martonvago.archelon.entity.enums.Beach
import com.martonvago.archelon.util.*
import org.junit.Before
import org.junit.Test
import org.threeten.bp.LocalDate
import org.threeten.bp.LocalDateTime
import org.threeten.bp.LocalTime

class ExtensionTest {

    lateinit var localDateTime: LocalDateTime

    @Before
    fun before() {
        localDateTime = LocalDateTime.of(2020, 12, 13, 11, 15)
    }

    @Test
    fun atTime_setsOnlyTime() {
        // given
        val newTime = LocalTime.of(3, 3)

        // when
        val updatedLocalDatetime = localDateTime.atTime(newTime.hour, newTime.minute)

        // then
        assertThat(updatedLocalDatetime.toLocalDate()).isEquivalentAccordingToCompareTo(localDateTime.toLocalDate())
        assertThat(updatedLocalDatetime.toLocalTime()).isEquivalentAccordingToCompareTo(newTime)
    }

    @Test
    fun atDate_setsOnlyDate() {
        // given
        val newDate = LocalDate.of(2021, 3, 3)

        // when
        val updatedLocalDatetime = localDateTime.atDate(newDate.year, newDate.monthValue, newDate.dayOfMonth)

        // then
        assertThat(updatedLocalDatetime.toLocalDate()).isEquivalentAccordingToCompareTo(newDate)
        assertThat(updatedLocalDatetime.toLocalTime()).isEquivalentAccordingToCompareTo(localDateTime.toLocalTime())
    }

    @Test
    fun displayTime_localDateTime_formatsTime() {
        // when
        val formatted = localDateTime.displayTime()

        // then
        assertThat(formatted).isEqualTo("11:15")
    }

    @Test
    fun displayTime_localTime_formatsTime() {
        // when
        val formatted = localDateTime.toLocalTime().displayTime()

        // then
        assertThat(formatted).isEqualTo("11:15")
    }

    @Test
    fun displayDate_formatsDate() {
        // when
        val formatted = localDateTime.displayDate()

        // then
        assertThat(formatted).isEqualTo("13/12/2020")
    }

    @Test
    fun toName_returnsEnumName() {
        // given
        val enum = Beach.MAVROVOUNI

        // when
        val name = enum.toName()

        // then
        assertThat(name).isEqualTo("MAVROVOUNI")
    }

    @Test
    fun toEnum_returnsEnumValue() {
        // given
        val name = "MAVROVOUNI"

        // when
        val enum = name.toEnum<Beach>()

        // then
        assertThat(enum).isEqualTo(Beach.MAVROVOUNI)
    }

    @Test
    fun enumValuesAsDisplayable_returnsDisplayable() {
        // when
        val values = Beach.enumValuesAsDisplayable()

        // then
        values.forEach {
            assertThat(it).isInstanceOf(Displayable::class.java)
        }
    }

    @Test
    fun asEnglishOrdinal_addsCorrectEnding() {
        // given
        mapOf(
            listOf(0, 4, 11, 12, 13, 14, 100, 111, 112, 113, 1000) to "th",
            listOf(1, 101, 1001) to "st",
            listOf(2, 102, 1002) to "nd",
            listOf(3, 103, 1003) to "rd"
        ).forEach { entry ->
            entry.key.forEach {
                // when + then
                assertThat(it.asEnglishOrdinal()).endsWith(entry.value)
                assertThat((it * -1).asEnglishOrdinal()).endsWith(entry.value)
            }
        }
    }
}