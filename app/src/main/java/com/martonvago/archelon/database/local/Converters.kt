package com.martonvago.archelon.database.local

import androidx.room.TypeConverter
import com.martonvago.archelon.entity.enums.*
import com.martonvago.archelon.util.toEnum
import com.martonvago.archelon.util.toName
import org.threeten.bp.LocalDateTime

/**
 * These converters are used by [ArchelonDatabase] to convert between custom data types and types
 * that Room can persist.
 */
class Converters {
    @TypeConverter fun strListToString(value: List<String>): String = value.joinToString(",")
    @TypeConverter fun stringToStrList(value: String): List<String> = value.split(",")

    @TypeConverter fun datetimeToString(value: LocalDateTime): String = value.toString()
    @TypeConverter fun stringToDatetime(value: String): LocalDateTime = LocalDateTime.parse(value)

    @TypeConverter fun skyToString(value: Sky) = value.toName()
    @TypeConverter fun stringToSky(value: String) = value.toEnum<Sky>()

    @TypeConverter fun beachToString(value: Beach) = value.toName()
    @TypeConverter fun stringToBeach(value: String) = value.toEnum<Beach>()

    @TypeConverter fun compassDirectionToString(value: CompassDirection) = value.toName()
    @TypeConverter fun stringToCompassDirection(value: String) = value.toEnum<CompassDirection>()

    @TypeConverter fun precipitationToString(value: Precipitation) = value.toName()
    @TypeConverter fun stringToPrecipitation(value: String) = value.toEnum<Precipitation>()

    @TypeConverter fun windIntensityToString(value: WindIntensity) = value.toName()
    @TypeConverter fun stringToWindIntensity(value: String) = value.toEnum<WindIntensity>()

    @TypeConverter fun surfToString(value: Surf) = value.toName()
    @TypeConverter fun stringToSurf(value: String) = value.toEnum<Surf>()
}