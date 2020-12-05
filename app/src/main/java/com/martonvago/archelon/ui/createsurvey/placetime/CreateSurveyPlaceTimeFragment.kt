package com.martonvago.archelon.ui.createsurvey.placetime

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.martonvago.archelon.R
import com.martonvago.archelon.di.hiltNavGraphViewModels
import com.martonvago.archelon.ui.createsurvey.CreateSurveyBaseFragment
import com.martonvago.archelon.viewmodel.CreateSurveyViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_create_survey_place_time.*

/**
 * A simple [Fragment] subclass.
 */
@AndroidEntryPoint
class CreateSurveyPlaceTimeFragment: CreateSurveyBaseFragment(
    R.layout.fragment_create_survey_place_time,
    false,
    null
) {
    private val viewModel by hiltNavGraphViewModels<CreateSurveyViewModel>(R.id.createSurveyNavGraph)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        startMorningSurveyButton.setOnClickListener {
            it.findNavController().navigate(R.id.action_createSurveyPlaceTimeFragment_to_createSurveyObserversFragment)
        }
    }
}
