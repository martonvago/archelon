package com.martonvago.archelon.viewmodel

import android.os.Build
import com.google.common.truth.Truth.assertThat
import com.martonvago.archelon.builders.SurveyWithEventsBuilder
import com.martonvago.archelon.di.RepositoryModule
import com.martonvago.archelon.repository.ArchelonRepository
import dagger.hilt.android.testing.*
import io.mockk.every
import io.mockk.mockk
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config


@UninstallModules(RepositoryModule::class)
@HiltAndroidTest
@Config(application = HiltTestApplication::class, sdk = [Build.VERSION_CODES.O_MR1])
@RunWith(RobolectricTestRunner::class)
class ViewSurveysViewModelTest {

    private val surveyWithEventsBuilder = SurveyWithEventsBuilder()

    private lateinit var viewSurveysViewModel: ViewSurveysViewModel

    @BindValue
    @JvmField
    val archelonRepository: ArchelonRepository = mockk(relaxed = true)

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Before
    fun before() {
        hiltRule.inject()
    }

    @Test
    fun fetchSurveys_loadsSurveys() {
        // given
        val surveysWithEvents = listOf(surveyWithEventsBuilder.build())
        every { archelonRepository.getAllSurveys().value } returns surveysWithEvents

        // when
        viewSurveysViewModel = ViewSurveysViewModel(archelonRepository)

        // then
        assertThat(viewSurveysViewModel.surveys.value).isNotNull()
        assertThat(viewSurveysViewModel.surveys.value!!.size).isEqualTo(1)
    }
}