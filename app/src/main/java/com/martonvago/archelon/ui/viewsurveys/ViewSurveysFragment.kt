package com.martonvago.archelon.ui.viewsurveys

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.martonvago.archelon.R
import com.martonvago.archelon.di.hiltNavGraphViewModels
import com.martonvago.archelon.entity.SurveyWithAdultEmergences
import com.martonvago.archelon.viewmodel.ViewSurveysViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_view_surveys.*

/**
 * A simple [Fragment] subclass.
 */
@AndroidEntryPoint
class ViewSurveysFragment : Fragment(R.layout.fragment_view_surveys) {
    private val viewModel by hiltNavGraphViewModels<ViewSurveysViewModel>(R.id.mainNavGraph)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val surveysObserver = Observer<List<SurveyWithAdultEmergences>> {
            surveyCount.text = it.size.toString()
        }
        viewModel.surveys.observe(viewLifecycleOwner, surveysObserver)
    }
}
