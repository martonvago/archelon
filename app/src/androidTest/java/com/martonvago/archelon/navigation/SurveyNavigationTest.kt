package com.martonvago.archelon.navigation

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelStore
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers
import com.google.common.truth.Truth.assertThat
import com.martonvago.archelon.R
import com.martonvago.archelon.launchFragmentInHiltContainer
import com.martonvago.archelon.ui.createsurvey.eventsmenu.MorningSurveyEventsMenuFragment
import com.martonvago.archelon.ui.createsurvey.menu.MorningSurveyMenuFragment
import com.martonvago.archelon.ui.createsurvey.observersweather.MorningSurveyObserversWeatherFragment
import com.martonvago.archelon.ui.createsurvey.placetime.MorningSurveyPlaceTimeFragment
import org.junit.jupiter.api.*

class SurveyNavigationTest {

    @Nested
    @DisplayName("Test navigation from and back to place/time screen")
    inner class PlaceTimeScreenNavigation {

        @BeforeEach
        fun beforeEach() {
            // given
            setupScenarioForFragment<MorningSurveyPlaceTimeFragment>()
            navController.setCurrentDestination(R.id.morningSurveyPlaceTimeFragment)
        }

        @Test
        fun navigatesFromPlaceTimeScreenToObserversScreen() {
            // when
            clickElementWithId(R.id.startMorningSurveyButton)

            // then
            assertThatCurrentDestinationIs(R.id.morningSurveyObserversWeatherFragment)
        }

        @Test
        fun navigatesFromObserversScreenToPlaceTimeScreen() {
            // given we have moved to the observers screen
            clickElementWithId(R.id.startMorningSurveyButton)

            // when
            clickElementWithId(R.id.prevButton)

            // then
            assertThatCurrentDestinationIs(R.id.morningSurveyPlaceTimeFragment)
        }
    }

    @Nested
    @DisplayName("Test navigation from and back to observers screen")
    inner class ObserversScreenNavigation {

        @BeforeEach
        fun beforeEach() {
            // given
            setupScenarioForFragment<MorningSurveyObserversWeatherFragment>()
            navController.setCurrentDestination(R.id.morningSurveyObserversWeatherFragment)
        }

        @Test
        fun navigatesFromObserversScreenToSurveyMenuScreen() {
            // when
            clickElementWithId(R.id.nextButton)

            // then
            assertThatCurrentDestinationIs(R.id.morningSurveyMenuFragment)
        }

        @Test
        fun opensCancelDialogFromObserversScreen() {
            assertThatCancelDialogOpens()
        }

        @Test
        fun navigatesFromSurveyMenuScreenToObserversScreen() {
            // given we have moved to the survey menu screen
            clickElementWithId(R.id.nextButton)

            // when
            clickElementWithId(R.id.prevButton)

            // then
            assertThatCurrentDestinationIs(R.id.morningSurveyObserversWeatherFragment)
        }
    }

    @Nested
    @DisplayName("Test navigation from and back to survey menu screen")
    inner class SurveyMenuScreenNavigation {

        @BeforeEach
        fun beforeEach() {
            // given
            setupScenarioForFragment<MorningSurveyMenuFragment>()
            navController.setCurrentDestination(R.id.morningSurveyMenuFragment)
        }

        @Test
        fun navigatesFromSurveyMenuScreenToEventsMenuScreen() {
            // when
            clickElementWithId(R.id.newEventButton)

            // then
            assertThatCurrentDestinationIs(R.id.morningSurveyEventsMenuFragment)
        }

        @Test
        fun opensEndSurveyDialogFromSurveyMenuScreen() {
            // when
            clickElementWithId(R.id.endSurveyButton)

            // then
            assertThatCurrentDestinationIs(R.id.endSurveyDialogFragment)
        }

        @Test
        fun opensCancelDialogFromSurveyMenuScreen() {
            assertThatCancelDialogOpens()
        }

        @Test
        fun navigatesFromEventsMenuScreenToSurveyMenuScreen() {
            // given we have moved to the events menu screen
            clickElementWithId(R.id.newEventButton)

            // when
            clickElementWithId(R.id.prevButton)

            // then
            assertThatCurrentDestinationIs(R.id.morningSurveyMenuFragment)
        }
    }

    @Test
    fun opensCancelDialogFromEventsMenuScreen() {
        // given
        setupScenarioForFragment<MorningSurveyEventsMenuFragment>()
        navController.setCurrentDestination(R.id.morningSurveyEventsMenuFragment)

        // then
        assertThatCancelDialogOpens()
    }

    companion object {
        lateinit var navController: TestNavHostController

        @BeforeAll
        @JvmStatic
        fun beforeAll() {
            navController = TestNavHostController(ApplicationProvider.getApplicationContext())
            navController.setViewModelStore(ViewModelStore())
            navController.setGraph(R.navigation.morning_survey_nav_graph)
        }

        fun assertThatCancelDialogOpens() {
            // when
            clickElementWithId(R.id.cancelButton)

            // then
            assertThatCurrentDestinationIs(R.id.cancelSurveyDialogFragment)
        }

        fun assertThatCurrentDestinationIs(destinationId: Int) {
            assertThat(navController.currentDestination?.id).isEqualTo(destinationId)
        }

        fun clickElementWithId(id: Int) {
            onView(ViewMatchers.withId(id)).perform(ViewActions.click())
        }

        inline fun <reified T : Fragment> setupScenarioForFragment() {
            launchFragmentInHiltContainer<T>(themeResId = R.style.AppTheme) {
                Navigation.setViewNavController(this.view!!, navController)
            }
        }
    }
}