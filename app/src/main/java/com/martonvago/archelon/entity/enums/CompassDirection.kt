package com.martonvago.archelon.entity.enums

import com.martonvago.archelon.entity.Displayable
import com.martonvago.archelon.entity.DisplayableCompanion
import kotlinx.android.parcel.Parcelize

@Parcelize
enum class CompassDirection(override val displayName: String): Displayable {
    NORTH("North"),
    SOUTH("South"),
    EAST("East"),
    WEST("West"),
    NORTHEAST("Northeast"),
    SOUTHEAST("Southeast"),
    SOUTHWEST("Southwest"),
    NORTHWEST("Northwest");

    companion object : DisplayableCompanion<CompassDirection>
}