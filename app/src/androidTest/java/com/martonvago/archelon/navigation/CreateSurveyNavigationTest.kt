package com.martonvago.archelon.navigation

import androidx.annotation.LayoutRes
import androidx.annotation.StringRes
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.scrollTo
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers
import com.google.common.truth.Truth.assertThat
import com.martonvago.archelon.R
import com.martonvago.archelon.entity.Displayable
import com.martonvago.archelon.entity.enumValuesAsDisplayable
import com.martonvago.archelon.entity.enums.*
import com.martonvago.archelon.ui.createsurvey.SelectArgs
import com.martonvago.archelon.ui.createsurvey.SelectFieldsAdapter
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

    @Test
    fun selectFieldClicked_onPlaceTimeScreen_opensBottomSheetDialog() {
        // given
        setupScenarioForFragment<CreateSurveyPlaceTimeFragment>()

        // when + then
        listOf(
            SelectTestArgs(0, R.string.beachSelectTitle, Beach.enumValuesAsDisplayable()),
            SelectTestArgs(1, R.string.beachSectorSelectTitle, CompassDirection.enumValuesAsDisplayable())
        ).forEach {
            testSelectField(it, R.id.createSurveyPlaceTimeFragment)
        }
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

    @Test
    fun selectFieldClicked_onObserversScreen_opensBottomSheetDialog() {
        // given
        setupScenarioForFragment<CreateSurveyObserversFragment>()

        // when + then
        listOf(
            SelectTestArgs(0, R.string.skySelectTitle, Sky.enumValuesAsDisplayable()),
            SelectTestArgs(1, R.string.precipitationSelectTitle, Precipitation.enumValuesAsDisplayable()),
            SelectTestArgs(2, R.string.windDirectionSelectTitle, CompassDirection.enumValuesAsDisplayable()),
            SelectTestArgs(3, R.string.windIntensitySelectTitle, WindIntensity.enumValuesAsDisplayable()),
            SelectTestArgs(4, R.string.surfSelectTitle, Surf.enumValuesAsDisplayable())
        ).forEach {
            testSelectField(it, R.id.createSurveyObserversFragment)
        }
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

    private data class SelectTestArgs(
        val position: Int,
        @StringRes val titleId: Int,
        val options: List<Displayable>
    )

    private fun testSelectField(args: SelectTestArgs, @LayoutRes layoutId: Int) {
        navController.setCurrentDestination(layoutId)
        clickSelectFieldAtPosition(args.position)
        assertThatSelectDialogOpenedCorrectly(args.titleId, args.options)
        navController.popBackStack()
    }

    private fun clickSelectFieldAtPosition(position: Int) {
        Espresso.onView(ViewMatchers.withId(R.id.selectFieldsContainer))
            .perform(actionOnItemAtPosition<SelectFieldsAdapter.ViewHolder>(position, scrollTo()))
            .perform(actionOnItemAtPosition<SelectFieldsAdapter.ViewHolder>(position, click()))
    }

    private fun assertThatSelectDialogOpenedCorrectly(@StringRes titleId: Int, options: List<Displayable>) {
        val args = navController.backStack.last().arguments
        assertThat(args).isNotNull()
        val selectArgs: SelectArgs = args!!.get("selectContent") as SelectArgs
        assertThat(selectArgs).isNotNull()
        assertThat(selectArgs.title).isEqualTo(titleId)
        assertThat(selectArgs.selectOptions).isEqualTo(options)
        assertThatCurrentDestinationIs(R.id.selectBottomSheetDialogFragment)
    }
}