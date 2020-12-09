package com.martonvago.archelon.entity.enums

import com.martonvago.archelon.entity.Displayable
import com.martonvago.archelon.entity.DisplayableCompanion
import kotlinx.android.parcel.Parcelize

/**
 * Sample options.
 */
@Parcelize
enum class Surf(override val displayName: String): Displayable {
    CALM("Calm"),
    SMALL("Small waves"),
    MEDIUM("Medium waves"),
    LARGE("Large waves");

    companion object : DisplayableCompanion<Surf>
}