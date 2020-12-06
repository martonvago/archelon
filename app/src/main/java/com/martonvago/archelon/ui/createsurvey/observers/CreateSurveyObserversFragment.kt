package com.martonvago.archelon.ui.createsurvey.observers

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import com.martonvago.archelon.R
import com.martonvago.archelon.databinding.FragmentCreateSurveyObserversBinding
import com.martonvago.archelon.ui.createsurvey.CreateSurveyBaseFragment
import kotlinx.android.synthetic.main.fragment_create_survey_observers.*

/**
 * A simple [Fragment] subclass.
 */
class CreateSurveyObserversFragment: CreateSurveyBaseFragment(
    true,
    R.id.action_createSurveyObserversFragment_to_createSurveyMenuFragment
) {
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
