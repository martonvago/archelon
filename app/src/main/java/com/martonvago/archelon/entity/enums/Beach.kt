package com.martonvago.archelon.entity.enums

import com.martonvago.archelon.entity.Displayable

/**
 * We use an enum to store the beaches, but they may be moved into their own table
 * if we need more flexibility.
 */
enum class Beach(override val displayName: String): Displayable {
    MAVROVOUNI("Mavrovouni"),
    SELINITSA("Selinitsa"),
    VATHI("Vathi"),
    VALTAKI("Valtaki")
}