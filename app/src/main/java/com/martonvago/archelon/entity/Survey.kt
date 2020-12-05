package com.martonvago.archelon.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import org.threeten.bp.LocalDateTime

// @JvmOverloads needed because we have an @Ignore-ed parameter
@Entity(tableName = "survey")
data class Survey @JvmOverloads constructor(
    @ColumnInfo(name = "date")
    var datetime: LocalDateTime,

    @ColumnInfo(name = "beach")
    var beach: Beach,

    @ColumnInfo(name = "beach_sector")
    var beachSector: CompassDirection,

    @ColumnInfo(name = "sky")
    var sky: Sky,

    @ColumnInfo(name = "precipitation")
    var precipitation: Precipitation,

    @ColumnInfo(name = "wind_intensity")
    var windIntensity: WindIntensity,

    @ColumnInfo(name = "wind_direction")
    var windDirection: CompassDirection,

    @ColumnInfo(name = "surf")
    var surf: Surf,

    @ColumnInfo(name = "leader")
    var leader: String,

    @ColumnInfo(name = "observers")
    var observers: List<String>,

    // Each event type has its own table
    @Ignore
    val events: List<ArchelonEvent> = emptyList()
) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int? = null
}