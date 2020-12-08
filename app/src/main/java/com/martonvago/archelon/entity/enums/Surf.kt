package com.martonvago.archelon.entity.enums

import com.martonvago.archelon.entity.Displayable

/**
 * Sample options.
 */
enum class Surf(override val displayName: String): Displayable {
    CALM("Calm"),
    SMALL("Small waves"),
    MEDIUM("Medium waves"),
    LARGE("Large waves")
}