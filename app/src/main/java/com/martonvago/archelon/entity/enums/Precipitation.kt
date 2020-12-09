package com.martonvago.archelon.entity.enums

import com.martonvago.archelon.entity.Displayable
import com.martonvago.archelon.entity.DisplayableCompanion
import kotlinx.android.parcel.Parcelize

/**
 * Sample options.
 */
@Parcelize
enum class Precipitation(override val displayName: String): Displayable {
    RAIN("Rain"),
    HAIL("Hail"),
    SNOW("Snow"),
    MIST("Mist");

    companion object : DisplayableCompanion<Precipitation>
}