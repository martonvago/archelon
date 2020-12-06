package com.martonvago.archelon.ui.createsurvey.dialogs

import com.martonvago.archelon.R

class EndSurveyDialogFragment: SurveyDialogFragment(
    R.string.endSurveyDialogTitle,
    R.string.endSurveyDialogDescription,
    R.id.endSurveyDialogFragment_to_homeScreenFragment
) {
    override fun yesButtonClicked() {
        viewModel.submitSurvey()
        super.yesButtonClicked()
    }

}