package com.martonvago.archelon.ui.morningsurvey

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.martonvago.archelon.R
import kotlinx.android.synthetic.main.fragment_cancel_survey_dialog.*

/**
 * A simple [Fragment] subclass.
 */
class CancelSurveyDialogFragment : DialogFragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_cancel_survey_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        noButton.setOnClickListener {
            findNavController().navigateUp()
        }

        yesButton.setOnClickListener {
            findNavController().navigate(R.id.cancelSurveyDialogFragment_to_homeScreenFragment)
        }
    }

}
