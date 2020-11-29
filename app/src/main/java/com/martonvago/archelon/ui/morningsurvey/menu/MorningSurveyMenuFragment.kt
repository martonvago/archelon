package com.martonvago.archelon.ui.morningsurvey.menu

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.martonvago.archelon.R

/**
 * A simple [Fragment] subclass.
 */
class MorningSurveyMenuFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_morning_survey_menu, container, false)
    }

}
