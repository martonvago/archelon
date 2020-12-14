package com.martonvago.archelon.repository

import androidx.room.Transaction
import com.martonvago.archelon.dao.local.AdultEmergenceDao
import com.martonvago.archelon.dao.local.HatchingDao
import com.martonvago.archelon.dao.local.SurveyDao
import com.martonvago.archelon.entity.SurveyWithEvents
import javax.inject.Inject

class ArchelonRepository @Inject constructor(
    private val surveyDao: SurveyDao,
    private val adultEmergenceDao: AdultEmergenceDao,
    private val hatchingDao: HatchingDao
) {
    fun getAllSurveys() = surveyDao.getSurveysWithEvents()

    @Transaction
    suspend fun saveSurveyWithEvents(surveyWithEvents: SurveyWithEvents) {
        val surveyId = surveyDao.save(surveyWithEvents.survey)

        surveyWithEvents.events.forEach {
            it.surveyId = surveyId
        }

        surveyWithEvents.adultEmergence.forEach {
            adultEmergenceDao.save(it)
        }

        surveyWithEvents.hatching.forEach {
            hatchingDao.save(it)
        }
    }
}