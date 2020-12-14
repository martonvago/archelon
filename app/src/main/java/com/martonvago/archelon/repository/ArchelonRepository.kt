package com.martonvago.archelon.repository

import com.martonvago.archelon.dao.local.AdultEmergenceDao
import com.martonvago.archelon.dao.local.HatchingDao
import com.martonvago.archelon.dao.local.SurveyDao
import com.martonvago.archelon.entity.Survey
import javax.inject.Inject

class ArchelonRepository @Inject constructor(
    private val surveyDao: SurveyDao,
    private val adultEmergenceDao: AdultEmergenceDao,
    private val hatchingDao: HatchingDao
) {
    suspend fun saveSurvey(survey: Survey) = surveyDao.save(survey)
    suspend fun getAllSurveys() = surveyDao.getSurveysWithAdultEmergences()
}