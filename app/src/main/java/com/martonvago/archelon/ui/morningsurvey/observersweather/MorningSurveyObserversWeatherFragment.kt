package com.martonvago.archelon.ui.morningsurvey.observersweather

import androidx.fragment.app.Fragment
import com.martonvago.archelon.R
import com.martonvago.archelon.di.hiltNavGraphViewModels
import com.martonvago.archelon.ui.morningsurvey.MorningSurveyScreen
import com.martonvago.archelon.viewmodel.MorningSurveyViewModel
import dagger.hilt.android.AndroidEntryPoint

/**
 * A simple [Fragment] subclass.
 */
@AndroidEntryPoint
class MorningSurveyObserversWeatherFragment: MorningSurveyScreen(
    R.layout.fragment_morning_survey_observers_weather,
    true,
    R.id.action_morningSurveyObserversWeatherFragment_to_morningSurveyMenuFragment
) {
    private val viewModel by hiltNavGraphViewModels<MorningSurveyViewModel>(R.id.morningSurveyNavigation)

}
