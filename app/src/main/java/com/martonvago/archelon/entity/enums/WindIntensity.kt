package com.martonvago.archelon.entity.enums

import com.martonvago.archelon.entity.Displayable
import com.martonvago.archelon.entity.DisplayableCompanion
import kotlinx.android.parcel.Parcelize

/**
 * Sample options.
 */
@Parcelize
enum class WindIntensity(override val displayName: String): Displayable {
    CALM("Calm"),
    LIGHT("Light breeze"),
    MODERATE("Moderate breeze"),
    STRONG("Strong gale"),
    STORM("Storm");

    companion object : DisplayableCompanion<WindIntensity>
}