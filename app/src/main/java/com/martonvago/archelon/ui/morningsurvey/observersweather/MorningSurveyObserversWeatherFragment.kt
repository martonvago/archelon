package com.martonvago.archelon.ui.morningsurvey.observersweather

import androidx.fragment.app.Fragment
import androidx.navigation.navGraphViewModels
import com.martonvago.archelon.R
import com.martonvago.archelon.ui.morningsurvey.MorningSurveyScreen
import com.martonvago.archelon.ui.morningsurvey.MorningSurveyViewModel

/**
 * A simple [Fragment] subclass.
 */
class MorningSurveyObserversWeatherFragment: MorningSurveyScreen(
    R.layout.fragment_morning_survey_observers_weather,
    true,
    R.id.action_morningSurveyObserversWeatherFragment_to_morningSurveyMenuFragment
) {
    private val viewModel: MorningSurveyViewModel by navGraphViewModels(R.id.morningSurveyNavigation)

}
