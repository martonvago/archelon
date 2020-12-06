package com.martonvago.archelon.ui.viewsurveys

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.martonvago.archelon.R
import com.martonvago.archelon.databinding.FragmentHomeScreenBinding
import com.martonvago.archelon.di.hiltNavGraphViewModels
import com.martonvago.archelon.viewmodel.ViewSurveysViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_home_screen.*

/**
 * A simple [Fragment] subclass.
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
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewModel = viewModel

        newMorningSurveyButton.setOnClickListener {
            it.findNavController().navigate(R.id.action_homeScreenFragment_to_morning_survey)
        }

        viewSurveysButton.setOnClickListener {
            viewModel.fetchSurveys()
            it.findNavController().navigate(R.id.action_homeScreenFragment_to_viewSurveysFragment)
        }
    }
}
