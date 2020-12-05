package com.martonvago.archelon.ui.createsurvey.dialogs

import com.martonvago.archelon.R
import com.martonvago.archelon.di.hiltNavGraphViewModels
import com.martonvago.archelon.viewmodel.CreateSurveyViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EndSurveyDialogFragment: SurveyDialogFragment(
    R.string.endSurveyDialogTitle,
    R.string.endSurveyDialogDescription,
    R.id.endSurveyDialogFragment_to_homeScreenFragment
) {
    private val viewModel by hiltNavGraphViewModels<CreateSurveyViewModel>(R.id.createSurveyNavGraph)

    override fun yesButtonClicked() {
        viewModel.submitSurvey()
        super.yesButtonClicked()
    }

}