package com.martonvago.archelon.entity

/**
 * We use an enum to store the beaches, but they may be moved into their own table
 * if we need more flexibility.
 */
enum class Beach(val displayName: String) {
    MAVROVOUNI("Mavrovouni"),
    SELINITSA("Selinitsa"),
    VATHI("Vathi"),
    VALTAKI("Valtaki")
}