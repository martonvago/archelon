package com.martonvago.archelon.ui.viewsurveys

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.martonvago.archelon.R
import com.martonvago.archelon.databinding.FragmentViewSurveysBinding
import com.martonvago.archelon.hilt.hiltNavGraphViewModels
import com.martonvago.archelon.viewmodel.ViewSurveysViewModel
import dagger.hilt.android.AndroidEntryPoint

/**
 * A fragment displaying the user's saved morning surveys.
 */
@AndroidEntryPoint
class ViewSurveysFragment : Fragment() {
    private val viewModel by hiltNavGraphViewModels<ViewSurveysViewModel>(R.id.mainNavGraph)
    lateinit var binding: FragmentViewSurveysBinding
    private val adapter = SavedSurveysAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentViewSurveysBinding.inflate(inflater)

        // We define the scope of the LiveData object bound to the fragment
        binding.lifecycleOwner = viewLifecycleOwner

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        binding.savedSurveysContainer.adapter = adapter

        // Whenever the value of surveys changes we update the data in the adapter
        viewModel.surveys.observe(viewLifecycleOwner) {
            adapter.setSurveys(it)
        }
    }
}
