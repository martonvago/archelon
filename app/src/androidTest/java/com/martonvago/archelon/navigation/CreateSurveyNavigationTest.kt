package com.martonvago.archelon.navigation

import com.martonvago.archelon.R
import com.martonvago.archelon.ui.createsurvey.eventsmenu.CreateSurveyEventsMenuFragment
import com.martonvago.archelon.ui.createsurvey.menu.CreateSurveyMenuFragment
import com.martonvago.archelon.ui.createsurvey.observers.CreateSurveyObserversFragment
import com.martonvago.archelon.ui.createsurvey.placetime.CreateSurveyPlaceTimeFragment
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.BeforeClass
import org.junit.Ignore
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class CreateSurveyNavigationTest: NavigationTestBase() {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    companion object {
        @BeforeClass
        @JvmStatic
        fun beforeAll(){
            setUpNavController(R.navigation.create_survey_nav_graph)
        }
    }

    // Place - time screen navigation

    @Test
    fun startSurveyClicked_navigatesFromPlaceTimeScreenToObserversScreen() {
        // given
        setupScenarioForFragment<CreateSurveyPlaceTimeFragment>()
        navController.setCurrentDestination(R.id.createSurveyPlaceTimeFragment)

        // when
        clickElementWithId(R.id.startMorningSurveyButton)

        // then
        assertThatCurrentDestinationIs(R.id.createSurveyObserversFragment)
    }

    @Test
    fun dateFieldClicked_opensDatePickerFromPlaceTimeScreen() {
        // given
        setupScenarioForFragment<CreateSurveyPlaceTimeFragment>()
        navController.setCurrentDestination(R.id.createSurveyPlaceTimeFragment)

        // when
        clickElementWithId(R.id.dateField)

        // then
        assertThatCurrentDestinationIs(R.id.datePickerFragment)
    }

    @Test
    fun timeFieldClicked_opensTimePickerFromPlaceTimeScreen() {
        // given
        setupScenarioForFragment<CreateSurveyPlaceTimeFragment>()
        navController.setCurrentDestination(R.id.createSurveyPlaceTimeFragment)

        // when
        clickElementWithId(R.id.timeField)

        // then
        assertThatCurrentDestinationIs(R.id.timePickerFragment)
    }

    @Test
    fun prevClicked_navigatesFromObserversScreenToPlaceTimeScreen() {
        // given we have moved to the observers screen
        setupScenarioForFragment<CreateSurveyPlaceTimeFragment>()
        navController.setCurrentDestination(R.id.createSurveyPlaceTimeFragment)
        clickElementWithId(R.id.startMorningSurveyButton)

        // when
        clickElementWithId(R.id.prevButton)

        // then
        assertThatCurrentDestinationIs(R.id.createSurveyPlaceTimeFragment)
    }

   // Observers screen navigation

    @Test
    fun nextClicked_navigatesFromObserversScreenToSurveyMenuScreen() {
        // given
        setupScenarioForFragment<CreateSurveyObserversFragment>()
        navController.setCurrentDestination(R.id.createSurveyObserversFragment)

        // when
        clickElementWithId(R.id.nextButton)

        // then
        assertThatCurrentDestinationIs(R.id.createSurveyMenuFragment)
    }

    @Test
    fun cancelClicked_opensCancelDialogFromObserversScreen() {
        // given
        setupScenarioForFragment<CreateSurveyObserversFragment>()
        navController.setCurrentDestination(R.id.createSurveyObserversFragment)

        // when + then
        assertThatCancelDialogOpens()
    }

    @Test
    fun prevClicked_navigatesFromSurveyMenuScreenToObserversScreen() {
        // given we have moved to the survey menu screen
        setupScenarioForFragment<CreateSurveyObserversFragment>()
        navController.setCurrentDestination(R.id.createSurveyObserversFragment)
        clickElementWithId(R.id.nextButton)

        // when
        clickElementWithId(R.id.prevButton)

        // then
        assertThatCurrentDestinationIs(R.id.createSurveyObserversFragment)
    }

    // Survey menu screen navigation

    @Test
    fun newEventClicked_navigatesFromSurveyMenuScreenToEventsMenuScreen() {
        // given
        setupScenarioForFragment<CreateSurveyMenuFragment>()
        navController.setCurrentDestination(R.id.createSurveyMenuFragment)

        // when
        clickElementWithId(R.id.newEventButton)

        // then
        assertThatCurrentDestinationIs(R.id.createSurveyEventsMenuFragment)
    }

    @Ignore("TODO: mock form valid")
    @Test
    fun endSurveyClicked_opensEndSurveyDialogFromSurveyMenuScreen() {
        // given
        setupScenarioForFragment<CreateSurveyMenuFragment>()
        navController.setCurrentDestination(R.id.createSurveyMenuFragment)

        // when
        clickElementWithId(R.id.endSurveyButton)

        // then
        assertThatCurrentDestinationIs(R.id.endSurveyDialogFragment)
    }

    @Test
    fun cancelClicked_opensCancelDialogFromSurveyMenuScreen() {
        // given
        setupScenarioForFragment<CreateSurveyMenuFragment>()
        navController.setCurrentDestination(R.id.createSurveyMenuFragment)

        // when + then
        assertThatCancelDialogOpens()
    }

    @Test
    fun prevClicked_navigatesFromEventsMenuScreenToSurveyMenuScreen() {
        // given we have moved to the events menu screen
        setupScenarioForFragment<CreateSurveyMenuFragment>()
        navController.setCurrentDestination(R.id.createSurveyMenuFragment)
        clickElementWithId(R.id.newEventButton)

        // when
        clickElementWithId(R.id.prevButton)

        // then
        assertThatCurrentDestinationIs(R.id.createSurveyMenuFragment)
    }

    // Events menu screen navigation

    @Test
    fun cancelClicked_opensCancelDialogFromEventsMenuScreen() {
        // given
        setupScenarioForFragment<CreateSurveyEventsMenuFragment>()
        navController.setCurrentDestination(R.id.createSurveyEventsMenuFragment)

        // when + then
        assertThatCancelDialogOpens()
    }

    private fun assertThatCancelDialogOpens() {
        // when
        clickElementWithId(R.id.cancelButton)

        // then
        assertThatCurrentDestinationIs(R.id.cancelSurveyDialogFragment)
    }
}