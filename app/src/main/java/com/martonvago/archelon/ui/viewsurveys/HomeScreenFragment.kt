package com.martonvago.archelon.ui.viewsurveys

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.martonvago.archelon.R
import com.martonvago.archelon.databinding.FragmentHomeScreenBinding
import com.martonvago.archelon.hilt.hiltNavGraphViewModels
import com.martonvago.archelon.ui.shared.setNavigateOnClickListener
import com.martonvago.archelon.viewmodel.ViewSurveysViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_home_screen.*

/**
 * A fragment for displaying the main menu on the home screen. This currently contains 2 options:
 * 'Add new morning survey' and 'View your morning surveys'.
 */
@AndroidEntryPoint
class HomeScreenFragment : Fragment() {
    private val viewModel by hiltNavGraphViewModels<ViewSurveysViewModel>(R.id.mainNavGraph)
    lateinit var binding: FragmentHomeScreenBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeScreenBinding.inflate(inflater)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewModel = viewModel

        newMorningSurveyButton.setNavigateOnClickListener(R.id.action_homeScreenFragment_to_morning_survey)
        viewSurveysButton.setNavigateOnClickListener(R.id.action_homeScreenFragment_to_viewSurveysFragment)
    }
}
