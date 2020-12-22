package com.martonvago.archelon.database

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.google.common.truth.Truth.assertThat
import com.martonvago.archelon.builders.AdultEmergenceBuilder
import com.martonvago.archelon.builders.HatchingBuilder
import com.martonvago.archelon.builders.SurveyBuilder
import com.martonvago.archelon.dao.local.AdultEmergenceDao
import com.martonvago.archelon.dao.local.HatchingDao
import com.martonvago.archelon.dao.local.SurveyDao
import com.martonvago.archelon.database.local.ArchelonDatabase
import getOrAwaitValue
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class ArchelonDatabaseTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val surveyBuilder = SurveyBuilder()
    private val adultEmergenceBuilder = AdultEmergenceBuilder()
    private val hatchingBuilder = HatchingBuilder()

    private lateinit var surveyDao: SurveyDao
    private lateinit var adultEmergenceDao: AdultEmergenceDao
    private lateinit var hatchingDao: HatchingDao
    private lateinit var db: ArchelonDatabase

    @Before
    fun before() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        // Using an in-memory database because the information stored here disappears when the
        // process is killed.
        db = Room.inMemoryDatabaseBuilder(context, ArchelonDatabase::class.java)
            // Allowing main thread queries, just for testing.
            .allowMainThreadQueries()
            .build()
        surveyDao = db.surveyDao
        adultEmergenceDao = db.adultEmergenceDao
        hatchingDao = db.hatchingDao
    }

    @After
    @Throws(IOException::class)
    fun after() {
        db.close()
    }

    @Test
    fun save_savesSurvey() = runBlocking {
        // when
        val surveyId = surveyDao.save(surveyBuilder.build())

        // then
        val surveys = surveyDao.getSurveysWithEvents().getOrAwaitValue()
        assertThat(surveys.size).isEqualTo(1)
        assertThat(surveys[0].survey.id).isEqualTo(surveyId)
    }

    @Test
    fun getSurveysWithEvents_noEvents_returnsSurveysWithEmptyEvents() = runBlocking {
        // given
        val surveyId1 = surveyDao.save(surveyBuilder.build())
        val surveyId2 = surveyDao.save(surveyBuilder.build())

        // when
        val surveys = surveyDao.getSurveysWithEvents().getOrAwaitValue()

        // then
        assertThat(surveys.size).isEqualTo(2)
        assertThat(surveys.map { it.survey.id }).isEqualTo(listOf(surveyId1, surveyId2))
        surveys.forEach {
            assertThat(it.events.size).isEqualTo(0)
            assertThat(it.adultEmergence.size).isEqualTo(0)
            assertThat(it.hatching.size).isEqualTo(0)
        }
    }

    @Test
    fun getSurveysWithEvents_withEvents_returnsSurveysWithEvents() = runBlocking {
        // given
        val surveyId1 = surveyDao.save(surveyBuilder.build())
        val surveyId2 = surveyDao.save(surveyBuilder.build())
        val adultEmergence = adultEmergenceBuilder.surveyId(surveyId1).build()
        val hatching = hatchingBuilder.surveyId(surveyId1).build()
        adultEmergenceDao.save(adultEmergence)
        hatchingDao.save(hatching)

        // when
        val surveys = surveyDao.getSurveysWithEvents().getOrAwaitValue()

        // then
        assertThat(surveys.size).isEqualTo(2)
        assertThat(surveys.map { it.survey.id }).isEqualTo(listOf(surveyId1, surveyId2))
        assertThat(surveys[0].events.size).isEqualTo(2)
        assertThat(surveys[0].adultEmergence.size).isEqualTo(1)
        assertThat(surveys[0].hatching.size).isEqualTo(1)
        assertThat(surveys[1].events.size).isEqualTo(0)
    }

    @Test
    fun save_savesAdultEmergence() = runBlocking {
        // given
        val surveyId = surveyDao.save(surveyBuilder.build())
        val adultEmergence = adultEmergenceBuilder.surveyId(surveyId).build()

        // when
        val emergenceId = adultEmergenceDao.save(adultEmergence)

        // then
        val adultEmergences = surveyDao.getSurveysWithEvents().getOrAwaitValue()[0].adultEmergence
        assertThat(adultEmergences.size).isEqualTo(1)
        assertThat(adultEmergences[0].id).isEqualTo(emergenceId)
    }

    @Test
    fun save_savesHatching() = runBlocking {
        // given
        val surveyId = surveyDao.save(surveyBuilder.build())
        val hatching = hatchingBuilder.surveyId(surveyId).build()

        // when
        val hatchingId = hatchingDao.save(hatching)

        // then
        val hatchings = surveyDao.getSurveysWithEvents().getOrAwaitValue()[0].hatching
        assertThat(hatchings.size).isEqualTo(1)
        assertThat(hatchings[0].id).isEqualTo(hatchingId)
    }
}