package com.martonvago.archelon.entity.enums

import com.martonvago.archelon.entity.Displayable
import com.martonvago.archelon.entity.DisplayableCompanion
import kotlinx.android.parcel.Parcelize

/**
 * Sample options.
 */
@Parcelize
enum class Sky(override val displayName: String): Displayable {
    SUNNY("Sunny"),
    CLOUDY("Cloudy"),
    FOGGY("Foggy");

    companion object : DisplayableCompanion<Sky>
}