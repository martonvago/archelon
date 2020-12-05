package com.martonvago.archelon.ui.viewsurveys

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.martonvago.archelon.R
import com.martonvago.archelon.di.hiltNavGraphViewModels
import com.martonvago.archelon.viewmodel.ViewSurveysViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_home_screen.*

/**
 * A simple [Fragment] subclass.
 */
@AndroidEntryPoint
class HomeScreenFragment : Fragment(R.layout.fragment_home_screen) {
    private val viewModel by hiltNavGraphViewModels<ViewSurveysViewModel>(R.id.mainNavGraph)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        newMorningSurveyButton.setOnClickListener {
            it.findNavController().navigate(R.id.action_homeScreenFragment_to_morning_survey)
        }

        viewSurveysButton.setOnClickListener {
            viewModel.fetchSurveys()
            it.findNavController().navigate(R.id.action_homeScreenFragment_to_viewSurveysFragment)
        }
    }

}
