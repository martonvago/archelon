package com.martonvago.archelon.ui.createsurvey.dialogs

import com.martonvago.archelon.R

/**
 * When the user abandons the survey without saving it, they return to the home screen
 * via this dialog.
 */
class CancelSurveyDialogFragment: SurveyDialogFragment(
    R.id.cancelSurveyDialogFragment_to_homeScreenFragment,
    R.string.cancelSurveyDialogTitle,
    R.string.cancelSurveyDialogDescription
)