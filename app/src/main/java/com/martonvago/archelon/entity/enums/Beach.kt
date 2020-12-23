package com.martonvago.archelon.entity.enums

import com.martonvago.archelon.entity.Displayable
import com.martonvago.archelon.entity.DisplayableCompanion
import kotlinx.android.parcel.Parcelize

/**
 * We use an enum to store the beaches, but they can be moved into their own table if it turns out
 * that they need to be updated by users or if we need to store more complex information about them.
 */
@Parcelize
enum class Beach(override val displayName: String): Displayable {
    MAVROVOUNI("Mavrovouni"),
    SELINITSA("Selinitsa"),
    VATHI("Vathi"),
    VALTAKI("Valtaki");

    companion object : DisplayableCompanion<Beach>
}