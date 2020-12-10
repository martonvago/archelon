package com.martonvago.archelon.ui.createsurvey.dialogs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.IdRes
import androidx.annotation.StringRes
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import com.martonvago.archelon.R
import com.martonvago.archelon.databinding.FragmentSurveyDialogBinding
import com.martonvago.archelon.di.hiltNavGraphViewModels
import com.martonvago.archelon.ui.shared.setNavigateOnClickListener
import com.martonvago.archelon.ui.shared.setNavigateUpOnClickListener
import com.martonvago.archelon.viewmodel.CreateSurveyViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_survey_dialog.*

/**
 * A simple [Fragment] subclass.
 */
@AndroidEntryPoint
abstract class SurveyDialogFragment(
    @StringRes private val dialogTitleId: Int,
    @StringRes private val dialogDescriptionId: Int,
    @IdRes private val yesButtonActionId: Int
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

        dialogTitle.setText(dialogTitleId)
        dialogDescription.setText(dialogDescriptionId)

        yesButton.setNavigateOnClickListener(yesButtonActionId, this) { beforeYesButtonClicked() }
        noButton.setNavigateUpOnClickListener(this)
    }

    open fun beforeYesButtonClicked() {}
}
