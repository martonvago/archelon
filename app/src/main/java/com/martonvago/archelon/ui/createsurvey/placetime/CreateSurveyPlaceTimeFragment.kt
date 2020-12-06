package com.martonvago.archelon.ui.createsurvey.placetime

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.martonvago.archelon.R
import com.martonvago.archelon.databinding.FragmentCreateSurveyPlaceTimeBinding
import com.martonvago.archelon.di.hiltNavGraphViewModels
import com.martonvago.archelon.ui.createsurvey.CreateSurveyBaseFragment
import com.martonvago.archelon.viewmodel.CreateSurveyViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_create_survey_place_time.*

/**
 * A simple [Fragment] subclass.
 */
@AndroidEntryPoint
class CreateSurveyPlaceTimeFragment: CreateSurveyBaseFragment(
    false,
    0
) {
    val viewModel by hiltNavGraphViewModels<CreateSurveyViewModel>(R.id.createSurveyNavGraph)
    lateinit var binding: FragmentCreateSurveyPlaceTimeBinding

    override fun initialiseContentBinding(inflater: LayoutInflater, wrapper: ViewGroup, attachToRoot: Boolean) {
        binding = FragmentCreateSurveyPlaceTimeBinding.inflate(inflater, wrapper, attachToRoot)
    }

    override fun populateContentBinding() {
        binding.viewModel = viewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        startMorningSurveyButton.setOnClickListener {
            it.findNavController().navigate(R.id.action_createSurveyPlaceTimeFragment_to_createSurveyObserversFragment)
        }
    }
}
