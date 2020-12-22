package com.martonvago.archelon.viewmodel

import android.os.Build
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import com.martonvago.archelon.di.ClockModule
import com.martonvago.archelon.di.RepositoryModule
import com.martonvago.archelon.entity.enums.*
import com.martonvago.archelon.repository.ArchelonRepository
import dagger.hilt.android.testing.*
import getOrAwaitValue
import io.mockk.coVerify
import io.mockk.mockk
import observeForTesting
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.threeten.bp.Clock
import org.threeten.bp.LocalDateTime

@UninstallModules(RepositoryModule::class, ClockModule::class)
@HiltAndroidTest
@Config(application = HiltTestApplication::class, sdk = [Build.VERSION_CODES.O_MR1])
@RunWith(RobolectricTestRunner::class)
class CreateSurveyViewModelTest {

    private lateinit var createSurveyViewModel: CreateSurveyViewModel

    @BindValue
    @JvmField
    val archelonRepository: ArchelonRepository = mockk(relaxed = true)

    @BindValue
    @JvmField
    val clock: Clock = mockk(relaxed = true)

    @get:Rule(order = 0)
    var hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun before() {
        hiltRule.inject()
        createSurveyViewModel = CreateSurveyViewModel(archelonRepository, clock)
    }

    @Test
    fun defaultState_initialisedCorrectly() {
        // then
        assertThat(createSurveyViewModel.dateTime.getContentValueOrDefault().toString())
            .isEqualTo(LocalDateTime.now(clock).toString())
        assertThat(createSurveyViewModel.leader.getContentValueOrDefault()).isEmpty()
        assertThat(createSurveyViewModel.observers.size).isEqualTo(3)
        createSurveyViewModel.observers.forEach {
            assertThat(it.getContentValueOrDefault()).isEmpty()
        }
        assertThat(createSurveyViewModel.beach.getContentValueOrDefault()).isEqualTo(Beach.MAVROVOUNI)
        assertThat(createSurveyViewModel.beachSector.getContentValueOrDefault()).isEqualTo(CompassDirection.EAST)
        assertThat(createSurveyViewModel.sky.getContentValueOrDefault()).isEqualTo(Sky.SUNNY)
        assertThat(createSurveyViewModel.precipitation.getContentValueOrDefault()).isEqualTo(Precipitation.NONE)
        assertThat(createSurveyViewModel.windIntensity.getContentValueOrDefault()).isEqualTo(WindIntensity.CALM)
        assertThat(createSurveyViewModel.windDirection.getContentValueOrDefault()).isEqualTo(CompassDirection.EAST)
        assertThat(createSurveyViewModel.surf.getContentValueOrDefault()).isEqualTo(Surf.CALM)
        assertThat(createSurveyViewModel.adultEmergenceEvents.size).isEqualTo(0)
        assertThat(createSurveyViewModel.hatchingEvents.size).isEqualTo(0)
        assertThat(createSurveyViewModel.formValid.getOrAwaitValue()).isFalse()
    }

    @Test
    fun updateTime_updatesTimeOfDateTimeField() {
        // given
        val newDateTime = createSurveyViewModel.dateTime.getContentValueOrDefault()
            .plusHours(1)
            .plusMinutes(5)

        // when
        createSurveyViewModel.updateTime(newDateTime.hour, newDateTime.minute)

        // then
        val dateTime = createSurveyViewModel.dateTime.content.getOrAwaitValue()
        assertThat(dateTime.hour).isEqualTo(newDateTime.hour)
        assertThat(dateTime.minute).isEqualTo(newDateTime.minute)
    }

    @Test
    fun updateDate_updatesDateOfDateTimeField() {
        // given
        val newDateTime = createSurveyViewModel.dateTime.getContentValueOrDefault()
            .plusYears(1)
            .plusMonths(1)
            .plusDays(1)

        // when
        createSurveyViewModel.updateDate(newDateTime.year, newDateTime.monthValue, newDateTime.dayOfMonth)

        // then
        val dateTime = createSurveyViewModel.dateTime.content.getOrAwaitValue()
        assertThat(dateTime.year).isEqualTo(newDateTime.year)
        assertThat(dateTime.monthValue).isEqualTo(newDateTime.monthValue)
        assertThat(dateTime.dayOfMonth).isEqualTo(newDateTime.dayOfMonth)
    }

    @Test
    fun addAdultEmergence_addsAdultEmergenceEvent() {
        // when
        createSurveyViewModel.addAdultEmergence()

        // then
        assertThat(createSurveyViewModel.adultEmergenceEvents.size).isEqualTo(1)
    }

    @Test
    fun addHatching_addsHatchingEvent() {
        // when
        createSurveyViewModel.addHatching()

        // then
        assertThat(createSurveyViewModel.hatchingEvents.size).isEqualTo(1)
    }

    @Test
    fun submitSurvey_savesSurveyWithEventsToRepo() {
        // given
        val leader = "Test"
        createSurveyViewModel.leader.setContentValue(leader)

        // when
        createSurveyViewModel.submitSurvey()

        // then
        coVerify(exactly = 1) { archelonRepository.saveSurveyWithEvents(withArg {
            assertThat(it.survey.leader).isEqualTo(leader)
        }) }
    }

    @Test
    fun formValid_allFieldsValid_isTrue() {
        // when
        createSurveyViewModel.leader.setContentValue("Test")

        // then
        assertThat(createSurveyViewModel.leader.valid.getOrAwaitValue()).isTrue()
        createSurveyViewModel.formValid.observeForTesting {
            assertThat(createSurveyViewModel.formValid.value).isTrue()
        }
    }
}