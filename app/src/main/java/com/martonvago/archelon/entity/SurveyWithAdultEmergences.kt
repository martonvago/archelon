package com.martonvago.archelon.entity

import androidx.room.Embedded
import androidx.room.Relation

/**
 * Class to set up one-to-many relationship between a survey and adult emergence events.
 */
data class SurveyWithAdultEmergences(
    @Embedded val survey: Survey,
    @Relation(
        parentColumn = "id",
        entityColumn = "survey_id"
    )
    val adultEmergences: List<AdultEmergence>
)