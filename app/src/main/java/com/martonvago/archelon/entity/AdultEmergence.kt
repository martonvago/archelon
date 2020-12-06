package com.martonvago.archelon.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "adult_emergence")
data class AdultEmergence(
    @ColumnInfo(name = "survey_id")
    var surveyId: Int
): ArchelonEvent {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int? = null
}