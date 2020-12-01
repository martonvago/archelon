package com.martonvago.archelon.ui.morningsurvey.menu

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.martonvago.archelon.R
import com.martonvago.archelon.ui.morningsurvey.MorningSurveyScreen
import kotlinx.android.synthetic.main.fragment_morning_survey_menu.*

/**
 * A simple [Fragment] subclass.
 */
class MorningSurveyMenuFragment: MorningSurveyScreen(R.layout.fragment_morning_survey_menu) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        newEventButton.setOnClickListener {
            it.findNavController().navigate(R.id.action_morningSurveyMenuFragment_to_morningSurveyEventsMenuFragment)
        }
    }
}
