package com.martonvago.archelon.builders

import com.martonvago.archelon.entity.Survey
import com.martonvago.archelon.entity.enums.*
import org.threeten.bp.LocalDateTime

data class SurveyBuilder (
    private var datetime: LocalDateTime = LocalDateTime.of(2020, 12, 14, 13, 20),
    private var beach: Beach = Beach.MAVROVOUNI,
    private var beachSector: CompassDirection = CompassDirection.EAST,
    private var sky: Sky = Sky.SUNNY,
    private var precipitation: Precipitation = Precipitation.HAIL,
    private var windIntensity: WindIntensity = WindIntensity.CALM,
    private var windDirection: CompassDirection = CompassDirection.NORTH,
    private var surf: Surf = Surf.CALM,
    private var leader: String = "Hermes",
    private var observers: List<String> = emptyList()
) {
    fun dateTime(dateTime: LocalDateTime) = apply { this.datetime = dateTime }
    fun beach(beach: Beach) = apply { this.beach = beach }
    fun beachSector(beachSector: CompassDirection) = apply { this.beachSector = beachSector }
    fun sky(sky: Sky) = apply { this.sky = sky }
    fun precipitation(precipitation: Precipitation) = apply { this.precipitation = precipitation }
    fun windIntensity(windIntensity: WindIntensity) = apply { this.windIntensity = windIntensity }
    fun windDirection(windDirection: CompassDirection) = apply { this.windDirection = windDirection }
    fun surf(surf: Surf) = apply { this.surf = surf }
    fun leader(leader: String) = apply { this.leader = leader }
    fun observers(observers: List<String>) = apply { this.observers = observers }

    fun build() = Survey(datetime, beach, beachSector, sky, precipitation, windIntensity, windDirection, surf, leader, observers)
}