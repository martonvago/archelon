package com.martonvago.archelon.entity

import androidx.room.Embedded
import androidx.room.Ignore
import androidx.room.Relation

/**
 * A class to set up a one-to-many relationship between a [Survey] and [ArchelonEvent]s.
 * Only two types of events are included for demonstration purposes.
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