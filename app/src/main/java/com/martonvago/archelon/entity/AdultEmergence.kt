package com.martonvago.archelon.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "adult_emergence")
data class AdultEmergence(
    @ColumnInfo(name = "survey_id")
    override var surveyId: Long? = null
): ArchelonEvent {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long? = null
}