package com.martonvago.archelon.ui.createsurvey.dialogs

import com.martonvago.archelon.R
import com.martonvago.archelon.databinding.FragmentSurveyDialogBinding
import com.martonvago.archelon.util.displayTime
import org.threeten.bp.LocalTime

/**
 * The user submits the completed survey via this dialog.
 */
class EndSurveyDialogFragment: SurveyDialogFragment(
    R.id.endSurveyDialogFragment_to_homeScreenFragment,
    R.string.endSurveyDialogTitle
) {

    override fun bind(binding: FragmentSurveyDialogBinding) {
        binding.description = resources.getString(
            R.string.endSurveyDialogDescription,
            LocalTime.now(viewModel.clock).displayTime()
        )
    }

    override fun beforeYesButtonClicked() {
        viewModel.submitSurvey()
    }
}