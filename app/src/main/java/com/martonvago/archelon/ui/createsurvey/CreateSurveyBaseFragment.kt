package com.martonvago.archelon.ui.createsurvey

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.martonvago.archelon.R
import com.martonvago.archelon.databinding.CreateSurveyWrapperBinding
import kotlinx.android.synthetic.main.create_survey_wrapper.*

/**
 * This is the base fragment for all morning survey screens. It provides customisable in-survey
 * navigation controls (prev, cancel, next) and uniform wrapper container styling.
 *
 * We avoid using default parameters in the constructor of a class which is the base class
 * for @AndroidEntryPoint classes; see https://github.com/google/dagger/issues/1904
 */
abstract class CreateSurveyBaseFragment(
    private val hasCancelButton: Boolean,
    private val nextActionId: Int
): Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = CreateSurveyWrapperBinding.inflate(inflater, container, false)

        initialiseContentBinding(inflater, binding.surveyContent, true)

        return binding.root
    }

    abstract fun initialiseContentBinding(inflater: LayoutInflater, wrapper: ViewGroup, attachToRoot: Boolean)

    abstract fun populateContentBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        populateContentBinding()

        prevButton.setOnClickListener {
            it.findNavController().popBackStack()
        }
        configureOptionalNavButton(
            cancelButton,
            if (hasCancelButton) R.id.action_global_cancelSurveyDialogFragment else null
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