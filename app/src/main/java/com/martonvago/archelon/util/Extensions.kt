package com.martonvago.archelon.util

// Adapted from: https://stackoverflow.com/questions/51102431/android-room-type-convert-multiple-enum-types
@Suppress("NOTHING_TO_INLINE")
inline fun <T : Enum<T>> T.toName(): String = this.name

inline fun <reified T : Enum<T>> String.toEnum(): T = enumValueOf(this)