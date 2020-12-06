package com.martonvago.archelon.ui.createsurvey.menu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.martonvago.archelon.R
import com.martonvago.archelon.databinding.FragmentCreateSurveyMenuBinding
import com.martonvago.archelon.ui.createsurvey.CreateSurveyBaseFragment
import kotlinx.android.synthetic.main.fragment_create_survey_menu.*

/**
 * A simple [Fragment] subclass.
 */
class CreateSurveyMenuFragment: CreateSurveyBaseFragment(true) {

    lateinit var binding: FragmentCreateSurveyMenuBinding

    override fun initialiseContentBinding(
        inflater: LayoutInflater,
        wrapper: ViewGroup,
        attachToRoot: Boolean
    ) {
        binding = FragmentCreateSurveyMenuBinding.inflate(inflater, wrapper, attachToRoot)
    }

    override fun populateContentBinding() {
        binding.viewModel = viewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        newEventButton.setOnClickListener {
            it.findNavController().navigate(R.id.action_createSurveyMenuFragment_to_createSurveyEventsMenuFragment)
        }

        endSurveyButton.setOnClickListener {
            it.findNavController().navigate(R.id.action_createSurveyMenuFragment_to_endSurveyDialogFragment)
        }
    }
}
