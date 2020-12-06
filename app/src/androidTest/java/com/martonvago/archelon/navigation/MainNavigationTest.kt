package com.martonvago.archelon.navigation

import com.martonvago.archelon.R
import com.martonvago.archelon.ui.viewsurveys.HomeScreenFragment
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class MainNavigationTest: NavigationTestBase() {

    companion object {
        @BeforeAll
        @JvmStatic
        fun beforeAll(){
            setUpNavController(R.navigation.main_nav_graph)
        }
    }

    @Nested
    inner class HomeScreenNavigation {

        @BeforeEach
        fun beforeEach() {
            // given
            setupScenarioForFragment<HomeScreenFragment>()
            navController.setCurrentDestination(R.id.homeScreenFragment)
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
}