package com.martonvago.archelon.navigation

import com.martonvago.archelon.R
import com.martonvago.archelon.builders.SurveyWithEventsBuilder
import com.martonvago.archelon.di.RepositoryModule
import com.martonvago.archelon.repository.ArchelonRepository
import com.martonvago.archelon.ui.viewsurveys.HomeScreenFragment
import dagger.hilt.android.testing.BindValue
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import io.mockk.every
import io.mockk.mockk
import org.junit.Before
import org.junit.BeforeClass
import org.junit.Rule
import org.junit.Test

@UninstallModules(RepositoryModule::class)
@HiltAndroidTest
class MainNavigationTest: NavigationTestBase() {

    @BindValue
    @JvmField
    val archelonRepository: ArchelonRepository = mockk(relaxed = true)

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    private val surveyWithEventsBuilder = SurveyWithEventsBuilder()

    companion object {
        @BeforeClass
        @JvmStatic
        fun beforeAll(){
            setUpNavController(R.navigation.main_nav_graph)
        }
    }

    @Before
    fun before() {
        setUpNavController(R.navigation.main_nav_graph)
        hiltRule.inject()
        val surveysWithEvents = listOf(surveyWithEventsBuilder.build())
        every { archelonRepository.getAllSurveys().value } returns surveysWithEvents
        setupScenarioForFragment<HomeScreenFragment>()
    }

    @Test
    fun navigatesFromHomeScreenToViewSurveysScreen() {
        // when
        clickElementWithId(R.id.viewSurveysButton)

        // then
        assertThatCurrentDestinationIs(R.id.viewSurveysFragment)
    }

    @Test
    fun navigatesFromViewSurveysScreenToHomeScreen() {
        // given we have moved to the view surveys screen
        clickElementWithId(R.id.viewSurveysButton)

        // when
        navController.popBackStack()

        // then
        assertThatCurrentDestinationIs(R.id.homeScreenFragment)
    }
}