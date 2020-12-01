package com.martonvago.archelon.ui.morningsurvey

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.martonvago.archelon.R
import kotlinx.android.synthetic.main.survey_screen_wrapper.*

abstract class MorningSurveyScreen(
    private val contentLayoutId: Int,
    private val hasCancelButton: Boolean = true,
    private val nextActionId: Int? = null
): Fragment() {

    lateinit var wrapper: View
    lateinit var content: View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        wrapper = inflater.inflate(R.layout.survey_screen_wrapper, container, false)
        content = inflater.inflate(contentLayoutId, wrapper as ViewGroup)
        return wrapper
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        prevButton.setOnClickListener {
            it.findNavController().popBackStack()
        }
        configureOptionalNavButton(
            cancelButton,
            if (hasCancelButton) R.id.action_global_homeScreenFragment else null
        )
        configureOptionalNavButton(nextButton, nextActionId)
    }

    private fun configureOptionalNavButton(button: View, navigationActionId: Int?) {
        if (navigationActionId != null) {
            button.setOnClickListener {
                it.findNavController().navigate(navigationActionId)
            }
        } else {
            button.visibility = View.GONE
        }
    }
}