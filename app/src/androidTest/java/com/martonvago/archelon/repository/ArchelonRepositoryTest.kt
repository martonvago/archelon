package com.martonvago.archelon.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import com.martonvago.archelon.builders.SurveyWithEventsBuilder
import com.martonvago.archelon.dao.local.AdultEmergenceDao
import com.martonvago.archelon.dao.local.HatchingDao
import com.martonvago.archelon.dao.local.SurveyDao
import com.martonvago.archelon.entity.SurveyWithEvents
import com.martonvago.archelon.getOrAwaitValue
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ArchelonRepositoryTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val surveyWithEventsBuilder = SurveyWithEventsBuilder()

    private val surveyId: Long = 99

    private lateinit var archelonRepository: ArchelonRepository

    @RelaxedMockK
    lateinit var surveyDao: SurveyDao

    @RelaxedMockK
    lateinit var adultEmergenceDao: AdultEmergenceDao

    @RelaxedMockK
    lateinit var hatchingDao: HatchingDao

    @Before
    fun before() {
        MockKAnnotations.init(this)
        archelonRepository = ArchelonRepository(surveyDao, adultEmergenceDao, hatchingDao)
        coEvery { surveyDao.save(any()) } returns surveyId
    }

    @Test
    fun getAllSurveys_noSurveys_returnsEmpty() {
        // given
        val surveys = MutableLiveData<List<SurveyWithEvents>>(emptyList())
        every { surveyDao.getSurveysWithEvents() } returns surveys

        // when
        val returnedSurveys = archelonRepository.getAllSurveys().getOrAwaitValue()

        // then
        assertThat(returnedSurveys.size).isEqualTo(0)
    }

    @Test
    fun getAllSurveys_surveys_returnsSurveysWithEvents() {
        // given
        val surveys = MutableLiveData(listOf(surveyWithEventsBuilder.build()))
        every { surveyDao.getSurveysWithEvents() } returns surveys

        // when
        val returnedSurveys = archelonRepository.getAllSurveys().getOrAwaitValue()

        // then
        assertThat(returnedSurveys.size).isEqualTo(1)
    }

    @Test
    fun saveSurveyWithEvents_noEvents_savesOnlySurvey() = runBlocking {
        // given
        val surveyWithEvents = surveyWithEventsBuilder.build()

        // when
        archelonRepository.saveSurveyWithEvents(surveyWithEvents)

        // then
        coVerify { surveyDao.save(surveyWithEvents.survey) }
        coVerify(exactly = 0) { adultEmergenceDao.save(any()) }
        coVerify(exactly = 0) { hatchingDao.save(any()) }
    }

    @Test
    fun saveSurveyWithEvents_events_savesSurveyAndEvents() = runBlocking {
        // given
        val surveyWithEvents = surveyWithEventsBuilder.withEvents().build()

        // when
        archelonRepository.saveSurveyWithEvents(surveyWithEvents)

        // then
        coVerify { surveyDao.save(surveyWithEvents.survey) }
        surveyWithEvents.adultEmergence.forEach {
            assertThat(it.surveyId).isEqualTo(surveyId)
            coVerify { adultEmergenceDao.save(it) }
        }
        surveyWithEvents.hatching.forEach {
            assertThat(it.surveyId).isEqualTo(surveyId)
            coVerify { hatchingDao.save(it) }
        }
    }
}