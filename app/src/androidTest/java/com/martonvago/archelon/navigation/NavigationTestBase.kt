package com.martonvago.archelon.navigation

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelStore
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers
import com.google.common.truth.Truth.assertThat
import com.martonvago.archelon.launchFragmentInHiltContainer

abstract class NavigationTestBase {

    // The nav controller is set up in @BeforeClass in the inheriting class, which has to be
    // enclosed in a companion object. So we have to enclose the shared setup logic
    // in a companion object here as well.
    companion object {
        lateinit var navController: TestNavHostController

        fun setUpNavController(navGraphId: Int) {
            navController = TestNavHostController(ApplicationProvider.getApplicationContext())
            navController.setViewModelStore(ViewModelStore())
            navController.setGraph(navGraphId)
        }
    }

    fun assertThatCurrentDestinationIs(destinationId: Int) {
        assertThat(navController.currentDestination?.id).isEqualTo(destinationId)
    }

    fun clickElementWithId(id: Int) {
        onView(ViewMatchers.withId(id)).perform(ViewActions.click())
    }

    inline fun <reified T : Fragment> setupScenarioForFragment() {
        launchFragmentInHiltContainer<T>(navController)
    }
}