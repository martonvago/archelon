package com.martonvago.archelon.entity.enums

import com.martonvago.archelon.entity.Displayable

/**
 * Sample options.
 */
enum class WindIntensity(override val displayName: String): Displayable {
    CALM("Calm"),
    LIGHT("Light breeze"),
    MODERATE("Moderate breeze"),
    STRONG("Strong gale"),
    STORM("Storm")
}