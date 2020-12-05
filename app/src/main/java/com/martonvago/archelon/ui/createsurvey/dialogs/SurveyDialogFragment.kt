package com.martonvago.archelon.ui.createsurvey.dialogs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.martonvago.archelon.R
import kotlinx.android.synthetic.main.fragment_survey_dialog.*

/**
 * A simple [Fragment] subclass.
 */
abstract class SurveyDialogFragment(
    private val dialogTitleId: Int,
    private val dialogDescriptionId: Int,
    private val yesButtonActionId: Int
) : DialogFragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_survey_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dialogTitle.setText(dialogTitleId)
        dialogDescription.setText(dialogDescriptionId)

        noButton.setOnClickListener {
            findNavController().navigateUp()
        }

        yesButton.setOnClickListener {
            yesButtonClicked()
        }
    }

    open fun yesButtonClicked() {
        findNavController().navigate(yesButtonActionId)
    }

}
