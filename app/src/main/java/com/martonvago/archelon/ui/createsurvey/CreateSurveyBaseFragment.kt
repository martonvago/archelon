package com.martonvago.archelon.ui.createsurvey

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import com.martonvago.archelon.R
import com.martonvago.archelon.databinding.CreateSurveyWrapperBinding
import com.martonvago.archelon.hilt.hiltNavGraphViewModels
import com.martonvago.archelon.ui.createsurvey.dialogs.select.SelectOptionArgs
import com.martonvago.archelon.ui.shared.configureOptionalNavButton
import com.martonvago.archelon.ui.shared.setNavigateUpOnClickListener
import com.martonvago.archelon.viewmodel.CreateSurveyViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.create_survey_wrapper.*

/**
 * This is the base fragment for all morning survey screens. It provides customisable in-survey
 * navigation controls (prev, cancel, next) and uniform wrapper container styling.
 *
 * We also use this class to set up data binding for the individual survey screens as well as
 * the shared view model, which is scoped to [R.id.createSurveyNavGraph].
 */
@AndroidEntryPoint
abstract class CreateSurveyBaseFragment(
    private val hasCancelButton: Boolean,
    @IdRes private val nextActionId: Int? = null
): Fragment() {

    val viewModel by hiltNavGraphViewModels<CreateSurveyViewModel>(R.id.createSurveyNavGraph)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = CreateSurveyWrapperBinding.inflate(inflater, container, false)

        initialiseContentBinding(inflater, binding.surveyContent, true)

        return binding.root
    }

    // Subclasses are responsible for inflating their own layout and setting their binding's lifecycleOwner
    abstract fun initialiseContentBinding(inflater: LayoutInflater, wrapper: ViewGroup, attachToRoot: Boolean)

    // Subclasses are responsible for binding values to their layout variables
    abstract fun populateContentBinding()

    fun createSelectFieldsAdapter(
        selectFieldArgs: List<SelectFieldArgs>,
        navAction: (SelectOptionArgs) -> NavDirections
    ) = SelectFieldsAdapter(
        selectFieldArgs,
        viewLifecycleOwner,
        { activity?.currentFocus?.clearFocus() },
        navAction
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        populateContentBinding()

        prevButton.setNavigateUpOnClickListener()
        cancelButton.configureOptionalNavButton(if (hasCancelButton) R.id.action_global_cancelSurveyDialogFragment else null)
        nextButton.configureOptionalNavButton(nextActionId)
    }
}