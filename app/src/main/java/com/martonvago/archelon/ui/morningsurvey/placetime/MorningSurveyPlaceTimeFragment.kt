package com.martonvago.archelon.ui.morningsurvey.placetime

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.navGraphViewModels
import com.martonvago.archelon.R
import com.martonvago.archelon.ui.morningsurvey.MorningSurveyScreen
import com.martonvago.archelon.viewmodel.MorningSurveyViewModel
import kotlinx.android.synthetic.main.fragment_morning_survey_place_time.*

/**
 * A simple [Fragment] subclass.
 */
class MorningSurveyPlaceTimeFragment: MorningSurveyScreen(
    R.layout.fragment_morning_survey_place_time,
    false
) {
    private val viewModel: MorningSurveyViewModel by navGraphViewModels(R.id.morningSurveyNavigation)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        startMorningSurveyButton.setOnClickListener {
            it.findNavController().navigate(R.id.action_morningSurveyPlaceTimeFragment_to_morningSurveyObserversWeatherFragment)
        }
    }
}
