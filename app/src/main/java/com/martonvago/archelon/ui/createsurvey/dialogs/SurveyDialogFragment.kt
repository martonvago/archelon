package com.martonvago.archelon.ui.createsurvey.dialogs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.IdRes
import androidx.annotation.StringRes
import androidx.fragment.app.DialogFragment
import com.martonvago.archelon.R
import com.martonvago.archelon.databinding.FragmentSurveyDialogBinding
import com.martonvago.archelon.hilt.hiltNavGraphViewModels
import com.martonvago.archelon.ui.shared.setNavigateOnClickListener
import com.martonvago.archelon.ui.shared.setNavigateUpOnClickListener
import com.martonvago.archelon.viewmodel.CreateSurveyViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_survey_dialog.*

/**
 * [CancelSurveyDialogFragment] and [EndSurveyDialogFragment] inherit from this dialog parent class.
 * We set up the bindings and inject the navgraph-scoped viewmodel here.
 */
@AndroidEntryPoint
abstract class SurveyDialogFragment(
    @IdRes private val yesButtonActionId: Int,
    @StringRes private val dialogTitleId: Int,
    @StringRes private val dialogDescriptionId: Int? = null
) : DialogFragment() {

    val viewModel by hiltNavGraphViewModels<CreateSurveyViewModel>(R.id.createSurveyNavGraph)
    lateinit var binding: FragmentSurveyDialogBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSurveyDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewModel = viewModel
        binding.title = dialogTitleId

        bind(binding)

        yesButton.setNavigateOnClickListener(yesButtonActionId, this) { beforeYesButtonClicked() }
        noButton.setNavigateUpOnClickListener(this)
    }

    // Subclasses can provide custom binding logic by overriding this method
    open fun bind(binding: FragmentSurveyDialogBinding) {
        // The description binding is type String to allow programmatic formatting
        binding.description = dialogDescriptionId?.let { resources.getString(it) }
    }

    // Subclasses can augment the behaviour triggered by clicking 'yes' by overriding this method
    open fun beforeYesButtonClicked() {}
}
