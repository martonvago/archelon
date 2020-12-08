package com.martonvago.archelon.entity.enums

import com.martonvago.archelon.entity.Displayable

/**
 * Sample options.
 */
enum class Sky(override val displayName: String): Displayable {
    SUNNY("Sunny"),
    CLOUDY("Cloudy"),
    FOGGY("Foggy")
}