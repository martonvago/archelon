package com.martonvago.archelon.ui.createsurvey.menu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.martonvago.archelon.R
import com.martonvago.archelon.databinding.FragmentCreateSurveyMenuBinding
import com.martonvago.archelon.ui.createsurvey.CreateSurveyBaseFragment
import com.martonvago.archelon.ui.shared.setNavigateOnClickListener
import kotlinx.android.synthetic.main.fragment_create_survey_menu.*

/**
 * This fragment holds the main menu of the create survey journey. Currently, only the
 * 'Add New Event' and 'End Survey' behaviours are implemented.
 */
class CreateSurveyMenuFragment: CreateSurveyBaseFragment(true) {

    lateinit var binding: FragmentCreateSurveyMenuBinding

    override fun initialiseContentBinding(
        inflater: LayoutInflater,
        wrapper: ViewGroup,
        attachToRoot: Boolean
    ) {
        binding = FragmentCreateSurveyMenuBinding.inflate(inflater, wrapper, attachToRoot)
        binding.lifecycleOwner = viewLifecycleOwner
    }

    override fun populateContentBinding() {
        binding.viewModel = viewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        newEventButton.setNavigateOnClickListener(R.id.action_createSurveyMenuFragment_to_createSurveyEventsMenuFragment)
        endSurveyButton.setNavigateOnClickListener(R.id.action_createSurveyMenuFragment_to_endSurveyDialogFragment)
    }
}
