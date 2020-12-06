package com.martonvago.archelon.ui.createsurvey.eventsmenu

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.martonvago.archelon.databinding.FragmentCreateSurveyEventsMenuBinding
import com.martonvago.archelon.ui.createsurvey.CreateSurveyBaseFragment

/**
 * A simple [Fragment] subclass.
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
