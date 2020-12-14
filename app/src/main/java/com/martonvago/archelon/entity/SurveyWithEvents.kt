package com.martonvago.archelon.entity

import androidx.room.Embedded
import androidx.room.Ignore
import androidx.room.Relation

/**
 * Class to set up one-to-many relationship between a survey and events. Only a few events
 * are included for demonstration purposes.
 */
data class SurveyWithEvents(
    @Embedded val survey: Survey,
    @Relation(
        parentColumn = "id",
        entityColumn = "survey_id"
    )
    val adultEmergence: List<AdultEmergence>,
    @Relation(
        parentColumn = "id",
        entityColumn = "survey_id"
    )
    val hatching: List<Hatching>
) {
    @Ignore
    val events: List<ArchelonEvent> = adultEmergence + hatching
}