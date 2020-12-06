package com.martonvago.archelon.navigation

import com.martonvago.archelon.R
import com.martonvago.archelon.ui.createsurvey.eventsmenu.CreateSurveyEventsMenuFragment
import com.martonvago.archelon.ui.createsurvey.menu.CreateSurveyMenuFragment
import com.martonvago.archelon.ui.createsurvey.observers.CreateSurveyObserversFragment
import com.martonvago.archelon.ui.createsurvey.placetime.CreateSurveyPlaceTimeFragment
import org.junit.jupiter.api.*

class CreateSurveyNavigationTest: NavigationTestBase() {

    companion object {
        @BeforeAll
        @JvmStatic
        fun beforeAll(){
            setUpNavController(R.navigation.create_survey_nav_graph)
        }
    }

    @Nested
    @DisplayName("Test navigation from and back to place/time screen")
    inner class PlaceTimeScreenNavigation {

        @BeforeEach
        fun beforeEach() {
            // given
            setupScenarioForFragment<CreateSurveyPlaceTimeFragment>()
            navController.setCurrentDestination(R.id.createSurveyPlaceTimeFragment)
        }

        @Test
        fun navigatesFromPlaceTimeScreenToObserversScreen() {
            // when
            clickElementWithId(R.id.startMorningSurveyButton)

            // then
            assertThatCurrentDestinationIs(R.id.createSurveyObserversFragment)
        }

        @Test
        fun navigatesFromObserversScreenToPlaceTimeScreen() {
            // given we have moved to the observers screen
            clickElementWithId(R.id.startMorningSurveyButton)

            // when
            clickElementWithId(R.id.prevButton)

            // then
            assertThatCurrentDestinationIs(R.id.createSurveyPlaceTimeFragment)
        }
    }

    @Nested
    @DisplayName("Test navigation from and back to observers screen")
    inner class ObserversScreenNavigation {

        @BeforeEach
        fun beforeEach() {
            // given
            setupScenarioForFragment<CreateSurveyObserversFragment>()
            navController.setCurrentDestination(R.id.createSurveyObserversFragment)
        }

        @Test
        fun navigatesFromObserversScreenToSurveyMenuScreen() {
            // when
            clickElementWithId(R.id.nextButton)

            // then
            assertThatCurrentDestinationIs(R.id.createSurveyMenuFragment)
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
            assertThatCurrentDestinationIs(R.id.createSurveyObserversFragment)
        }
    }

    @Nested
    @DisplayName("Test navigation from and back to survey menu screen")
    inner class SurveyMenuScreenNavigation {

        @BeforeEach
        fun beforeEach() {
            // given
            setupScenarioForFragment<CreateSurveyMenuFragment>()
            navController.setCurrentDestination(R.id.createSurveyMenuFragment)
        }

        @Test
        fun navigatesFromSurveyMenuScreenToEventsMenuScreen() {
            // when
            clickElementWithId(R.id.newEventButton)

            // then
            assertThatCurrentDestinationIs(R.id.createSurveyEventsMenuFragment)
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
            assertThatCurrentDestinationIs(R.id.createSurveyMenuFragment)
        }
    }

    @Test
    fun opensCancelDialogFromEventsMenuScreen() {
        // given
        setupScenarioForFragment<CreateSurveyEventsMenuFragment>()
        navController.setCurrentDestination(R.id.createSurveyEventsMenuFragment)

        // then
        assertThatCancelDialogOpens()
    }

    private fun assertThatCancelDialogOpens() {
        // when
        clickElementWithId(R.id.cancelButton)

        // then
        assertThatCurrentDestinationIs(R.id.cancelSurveyDialogFragment)
    }
}