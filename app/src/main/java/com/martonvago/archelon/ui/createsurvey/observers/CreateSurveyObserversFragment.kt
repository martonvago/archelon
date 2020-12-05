package com.martonvago.archelon.ui.createsurvey.observers

import androidx.fragment.app.Fragment
import com.martonvago.archelon.R
import com.martonvago.archelon.di.hiltNavGraphViewModels
import com.martonvago.archelon.ui.createsurvey.CreateSurveyBaseFragment
import com.martonvago.archelon.viewmodel.CreateSurveyViewModel
import dagger.hilt.android.AndroidEntryPoint

/**
 * A simple [Fragment] subclass.
 */
@AndroidEntryPoint
class CreateSurveyObserversFragment: CreateSurveyBaseFragment(
    R.layout.fragment_create_survey_observers,
    true,
    R.id.action_createSurveyObserversFragment_to_createSurveyMenuFragment
) {
    private val viewModel by hiltNavGraphViewModels<CreateSurveyViewModel>(R.id.createSurveyNavGraph)

}
