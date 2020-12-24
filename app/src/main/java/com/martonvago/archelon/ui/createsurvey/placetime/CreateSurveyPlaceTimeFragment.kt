package com.martonvago.archelon.ui.createsurvey.placetime

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.martonvago.archelon.R
import com.martonvago.archelon.databinding.FragmentCreateSurveyPlaceTimeBinding
import com.martonvago.archelon.entity.enumValuesAsDisplayable
import com.martonvago.archelon.entity.enums.Beach
import com.martonvago.archelon.entity.enums.CompassDirection
import com.martonvago.archelon.ui.createsurvey.CreateSurveyBaseFragment
import com.martonvago.archelon.ui.createsurvey.SelectFieldArgs
import com.martonvago.archelon.ui.createsurvey.dialogs.select.SelectOptionArgs
import com.martonvago.archelon.ui.shared.setNavigateOnClickListener
import kotlinx.android.synthetic.main.fragment_create_survey_place_time.*

/**
 * This fragment is where the user enters the beach, beach sector, time and date.
 */
class CreateSurveyPlaceTimeFragment : CreateSurveyBaseFragment(false) {
    lateinit var binding: FragmentCreateSurveyPlaceTimeBinding

    override fun initialiseContentBinding(inflater: LayoutInflater, wrapper: ViewGroup, attachToRoot: Boolean) {
        binding = FragmentCreateSurveyPlaceTimeBinding.inflate(inflater, wrapper, attachToRoot)
        binding.lifecycleOwner = viewLifecycleOwner
    }

    override fun populateContentBinding() {
        binding.viewModel = viewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val selectFields = listOf(
            SelectFieldArgs(viewModel.beach, R.string.beach, R.string.beachSelectTitle, Beach.enumValuesAsDisplayable()),
            SelectFieldArgs(viewModel.beachSector, R.string.beachSector, R.string.beachSectorSelectTitle, CompassDirection.enumValuesAsDisplayable())
        )

        selectFieldsContainer.adapter = createSelectFieldsAdapter(selectFields) { selectOptionArgs: SelectOptionArgs ->
            CreateSurveyPlaceTimeFragmentDirections
                .actionCreateSurveyPlaceTimeFragmentToSelectBottomSheetDialogFragment(selectOptionArgs)
        }

        dateField.setNavigateOnClickListener(R.id.action_createSurveyPlaceTimeFragment_to_datePickerFragment)
        timeField.setNavigateOnClickListener(R.id.action_createSurveyPlaceTimeFragment_to_timePickerFragment)
        startMorningSurveyButton.setNavigateOnClickListener(R.id.action_createSurveyPlaceTimeFragment_to_createSurveyObserversFragment)
    }
}
