package com.martonvago.archelon.ui.createsurvey.observers

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import com.martonvago.archelon.R
import com.martonvago.archelon.databinding.FragmentCreateSurveyObserversBinding
import com.martonvago.archelon.di.hiltNavGraphViewModels
import com.martonvago.archelon.ui.createsurvey.CreateSurveyBaseFragment
import com.martonvago.archelon.viewmodel.CreateSurveyViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_create_survey_observers.*

/**
 * A simple [Fragment] subclass.
 */
@AndroidEntryPoint
class CreateSurveyObserversFragment: CreateSurveyBaseFragment(
    true,
    R.id.action_createSurveyObserversFragment_to_createSurveyMenuFragment
) {
    val viewModel by hiltNavGraphViewModels<CreateSurveyViewModel>(R.id.createSurveyNavGraph)
    lateinit var binding: FragmentCreateSurveyObserversBinding

    override fun initialiseContentBinding(inflater: LayoutInflater, wrapper: ViewGroup, attachToRoot: Boolean) {
        binding = FragmentCreateSurveyObserversBinding.inflate(inflater, wrapper, attachToRoot)
    }

    override fun populateContentBinding() {
        binding.viewModel = viewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        leaderInput.doOnTextChanged { text, _, _, _ ->
            viewModel.leader = text.toString()
        }
    }
}
