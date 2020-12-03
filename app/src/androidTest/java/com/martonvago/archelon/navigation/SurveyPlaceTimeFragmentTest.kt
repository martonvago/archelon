package com.martonvago.archelon.navigation

import androidx.fragment.app.Fragment
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import com.martonvago.archelon.R
import com.martonvago.archelon.ui.morningsurvey.placetime.MorningSurveyPlaceTimeFragment
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SurveyPlaceTimeFragmentTest {

    private lateinit var navController: TestNavHostController

    @Before
    fun setup() {
        navController = TestNavHostController(ApplicationProvider.getApplicationContext())
        navController.setGraph(R.navigation.morning_survey_nav_graph)
    }

    @Test
    fun testNavigationFromPlaceTimeScreenToObserversScreen() {
        // given
        setupScenarioForFragment<MorningSurveyPlaceTimeFragment>()

        // when
        onView(ViewMatchers.withId(R.id.startMorningSurveyButton)).perform(ViewActions.click())

        // then
        assertThat(navController.currentDestination?.id).isEqualTo(R.id.morningSurveyObserversWeatherFragment)
    }

    private inline fun <reified T: Fragment> setupScenarioForFragment() {
        val scenario = launchFragmentInContainer<T>(themeResId = R.style.AppTheme)
        scenario.onFragment { fragment ->
            Navigation.setViewNavController(fragment.requireView(), navController)
        }
    }
}