package com.martonvago.archelon.dao.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.martonvago.archelon.entity.Survey
import com.martonvago.archelon.entity.SurveyWithAdultEmergences

@Dao
interface SurveyDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(survey: Survey)

    // Retrieve surveys together with the emergence events that belong to them
    @Transaction
    @Query("SELECT * FROM survey")
    fun getSurveysWithAdultEmergences(): LiveData<List<SurveyWithAdultEmergences>>
}