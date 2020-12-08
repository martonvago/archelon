package com.martonvago.archelon.entity.enums

import com.martonvago.archelon.entity.Displayable

enum class CompassDirection(override val displayName: String): Displayable {
    NORTH("North"),
    SOUTH("South"),
    EAST("East"),
    WEST("West"),
    NORTHEAST("Northeast"),
    SOUTHEAST("Southeast"),
    SOUTHWEST("Southwest"),
    NORTHWEST("Northwest")
}