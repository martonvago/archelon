package com.martonvago.archelon.util

import org.threeten.bp.LocalDateTime
import org.threeten.bp.LocalTime
import org.threeten.bp.format.DateTimeFormatter
import kotlin.math.abs

/**
 * Adapted from: https://stackoverflow.com/questions/51102431/android-room-type-convert-multiple-enum-types
 */
@Suppress("NOTHING_TO_INLINE")
inline fun <T : Enum<T>> T.toName(): String = this.name

inline fun <reified T : Enum<T>> String.toEnum(): T = enumValueOf(this)

fun LocalDateTime.atTime(hour: Int, minute: Int): LocalDateTime = this.withHour(hour).withMinute(minute)
fun LocalDateTime.atDate(year: Int, month: Int, day: Int): LocalDateTime = this.withYear(year).withMonth(month).withDayOfMonth(day)
fun LocalDateTime.displayTime(): String = this.format(DateTimeFormatter.ofPattern("HH:mm"))
fun LocalDateTime.displayDate(): String = this.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
fun LocalTime.displayTime(): String = this.format(DateTimeFormatter.ofPattern("HH:mm"))

fun Int.asEnglishOrdinal(): String {
    val ordinal = if (abs(this) % 100 in 11..14) {
        "th"
    } else {
        when (abs(this) % 10) {
            1 -> "st"
            2 -> "nd"
            3 -> "rd"
            else -> "th"
        }
    }
    return "$this$ordinal"
}