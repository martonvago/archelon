package com.martonvago.archelon.dao.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.martonvago.archelon.entity.Survey
import com.martonvago.archelon.entity.SurveyWithEvents

@Dao
interface SurveyDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(survey: Survey): Long

    // Retrieve surveys together with the events that belong to them
    @Transaction
    @Query("SELECT * FROM survey")
    fun getSurveysWithEvents(): LiveData<List<SurveyWithEvents>>
}