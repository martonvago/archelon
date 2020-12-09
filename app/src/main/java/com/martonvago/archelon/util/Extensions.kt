package com.martonvago.archelon.util

import com.martonvago.archelon.entity.Displayable
import com.martonvago.archelon.entity.DisplayableCompanion
import org.threeten.bp.LocalDateTime
import org.threeten.bp.format.DateTimeFormatter

/**
 * Adapted from: https://stackoverflow.com/questions/51102431/android-room-type-convert-multiple-enum-types
 */
@Suppress("NOTHING_TO_INLINE")
inline fun <T : Enum<T>> T.toName(): String = this.name

/**
 * Casts the values of a displayable enum to [Displayable], used mainly as a shorthand for
 * generating options for select input components from enums
 */
inline fun <reified T> DisplayableCompanion<T>.enumValuesAsDisplayable(): List<Displayable>
        where T : Enum<T>, T : Displayable = enumValues<T>().map { it }

inline fun <reified T : Enum<T>> String.toEnum(): T = enumValueOf(this)

fun LocalDateTime.atTime(hour: Int, minute: Int): LocalDateTime = this.withHour(hour).withMinute(minute)
fun LocalDateTime.atDate(year: Int, month: Int, day: Int): LocalDateTime = this.withYear(year).withMonth(month).withDayOfMonth(day)
fun LocalDateTime.displayTime(): String = this.format(DateTimeFormatter.ofPattern("HH:mm"))
fun LocalDateTime.displayDate(): String = this.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))