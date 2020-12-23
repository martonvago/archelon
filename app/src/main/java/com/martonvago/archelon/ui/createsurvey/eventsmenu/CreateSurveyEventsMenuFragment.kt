package com.martonvago.archelon.ui.createsurvey.eventsmenu

import android.view.LayoutInflater
import android.view.ViewGroup
import com.martonvago.archelon.databinding.FragmentCreateSurveyEventsMenuBinding
import com.martonvago.archelon.ui.createsurvey.CreateSurveyBaseFragment

/**
 * This fragment holds the menu where the user decides what events to add to a morning survey.
 * Currently, we only support adding demo adult emergence and hatching events, without
 * any customisation or details. Each event button would eventually launch its own
 * create event form / screen sequence.
 */
class CreateSurveyEventsMenuFragment: CreateSurveyBaseFragment(true) {

    lateinit var binding: FragmentCreateSurveyEventsMenuBinding

    override fun initialiseContentBinding(
        inflater: LayoutInflater,
        wrapper: ViewGroup,
        attachToRoot: Boolean
    ) {
        binding = FragmentCreateSurveyEventsMenuBinding.inflate(inflater, wrapper, attachToRoot)
    }

    override fun populateContentBinding() {
        binding.viewModel = viewModel
    }
}
