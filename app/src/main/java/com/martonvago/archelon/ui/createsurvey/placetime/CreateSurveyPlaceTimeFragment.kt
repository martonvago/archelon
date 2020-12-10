package com.martonvago.archelon.ui.createsurvey.placetime

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.martonvago.archelon.R
import com.martonvago.archelon.databinding.FragmentCreateSurveyPlaceTimeBinding
import com.martonvago.archelon.entity.enumValuesAsDisplayable
import com.martonvago.archelon.entity.enums.Beach
import com.martonvago.archelon.entity.enums.CompassDirection
import com.martonvago.archelon.ui.createsurvey.CreateSurveyBaseFragment
import com.martonvago.archelon.ui.createsurvey.SelectArgs
import com.martonvago.archelon.ui.shared.WithSelectField
import com.martonvago.archelon.ui.shared.setNavigateOnClickListener
import kotlinx.android.synthetic.main.fragment_create_survey_place_time.*

/**
 * A simple [Fragment] subclass.
 */
class CreateSurveyPlaceTimeFragment : CreateSurveyBaseFragment(false), WithSelectField {
    override val navActionToSelectDialog = { selectArgs: SelectArgs -> CreateSurveyPlaceTimeFragmentDirections.actionCreateSurveyPlaceTimeFragmentToSelectBottomSheetDialogFragment(selectArgs)}
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

        setSelectFieldOnCLickListener(
            beachField,
            viewModel.beach,
            Beach.enumValuesAsDisplayable(),
            R.string.beachSelectTitle
        )
        setSelectFieldOnCLickListener(
            beachSectorField,
            viewModel.beachSector,
            CompassDirection.enumValuesAsDisplayable(),
            R.string.beachSectorSelectTitle
        )

        dateField.setNavigateOnClickListener(R.id.action_createSurveyPlaceTimeFragment_to_datePickerFragment)
        timeField.setNavigateOnClickListener(R.id.action_createSurveyPlaceTimeFragment_to_timePickerFragment)
        startMorningSurveyButton.setNavigateOnClickListener(R.id.action_createSurveyPlaceTimeFragment_to_createSurveyObserversFragment)
    }
}
