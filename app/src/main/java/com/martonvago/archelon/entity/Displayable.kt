package com.martonvago.archelon.entity

import android.os.Parcelable

/**
 * An interface implemented by enums which encode user input options.
 */
interface Displayable: Parcelable {
    val displayName: String
}

/**
 * Used to attach the toDisplayable extension function to enums which are used as options
 * in select input elements.
 */
interface DisplayableCompanion<T : Enum<T>>

/**
 * Casts the values of a displayable enum to [Displayable], used mainly as a shorthand for
 * generating options for select input components from enums.
 */
inline fun <reified T> DisplayableCompanion<T>.enumValuesAsDisplayable(): List<Displayable>
        where T : Enum<T>, T : Displayable = enumValues<T>().map { it }