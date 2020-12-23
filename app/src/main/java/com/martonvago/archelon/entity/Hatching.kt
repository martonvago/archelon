package com.martonvago.archelon.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * The full implementation of this entity with all its fields is out of scope for this project.
 */
@Entity(tableName = "hatching")
data class Hatching(
    @ColumnInfo(name = "survey_id")
    override var surveyId: Long? = null
): ArchelonEvent {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long? = null
}