package com.martonvago.archelon.ui.morningsurvey.placetime

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.martonvago.archelon.R
import com.martonvago.archelon.di.hiltNavGraphViewModels
import com.martonvago.archelon.ui.morningsurvey.MorningSurveyScreen
import com.martonvago.archelon.viewmodel.MorningSurveyViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_morning_survey_place_time.*

/**
 * A simple [Fragment] subclass.
 */
@AndroidEntryPoint
class MorningSurveyPlaceTimeFragment: MorningSurveyScreen(
    R.layout.fragment_morning_survey_place_time,
    false,
    null
) {
    private val viewModel by hiltNavGraphViewModels<MorningSurveyViewModel>(R.id.morningSurveyNavigation)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        startMorningSurveyButton.setOnClickListener {
            viewModel.addTestSurvey()
            viewModel.dumpSurveyData()
            it.findNavController().navigate(R.id.action_morningSurveyPlaceTimeFragment_to_morningSurveyObserversWeatherFragment)
        }
    }
}
