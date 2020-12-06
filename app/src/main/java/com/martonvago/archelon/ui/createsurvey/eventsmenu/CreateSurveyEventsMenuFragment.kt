package com.martonvago.archelon.ui.createsurvey.eventsmenu

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.martonvago.archelon.R
import com.martonvago.archelon.databinding.FragmentCreateSurveyEventsMenuBinding
import com.martonvago.archelon.di.hiltNavGraphViewModels
import com.martonvago.archelon.ui.createsurvey.CreateSurveyBaseFragment
import com.martonvago.archelon.viewmodel.CreateSurveyViewModel
import dagger.hilt.android.AndroidEntryPoint

/**
 * A simple [Fragment] subclass.
 */
@AndroidEntryPoint
class CreateSurveyEventsMenuFragment: CreateSurveyBaseFragment(true, 0) {

    val viewModel by hiltNavGraphViewModels<CreateSurveyViewModel>(R.id.createSurveyNavGraph)
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
