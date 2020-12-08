package com.martonvago.archelon.entity.enums

import com.martonvago.archelon.entity.Displayable

/**
 * Sample options.
 */
enum class Precipitation(override val displayName: String): Displayable {
    RAIN("Rain"),
    HAIL("Hail"),
    SNOW("Snow"),
    MIST("Mist")
}