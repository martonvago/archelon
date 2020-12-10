package com.martonvago.archelon.ui.createsurvey.observers

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.martonvago.archelon.R
import com.martonvago.archelon.databinding.FragmentCreateSurveyObserversBinding
import com.martonvago.archelon.entity.enumValuesAsDisplayable
import com.martonvago.archelon.entity.enums.*
import com.martonvago.archelon.ui.createsurvey.CreateSurveyBaseFragment
import com.martonvago.archelon.ui.createsurvey.SelectArgs
import com.martonvago.archelon.ui.shared.WithSelectField
import kotlinx.android.synthetic.main.fragment_create_survey_observers.*

/**
 * A simple [Fragment] subclass.
 */
class CreateSurveyObserversFragment: CreateSurveyBaseFragment(
    true,
    R.id.action_createSurveyObserversFragment_to_createSurveyMenuFragment
), WithSelectField {
    override val navActionToSelectDialog = { selectArgs: SelectArgs -> CreateSurveyObserversFragmentDirections.actionCreateSurveyObserversFragmentToSelectBottomSheetDialogFragment(selectArgs)}
    lateinit var binding: FragmentCreateSurveyObserversBinding

    override fun initialiseContentBinding(inflater: LayoutInflater, wrapper: ViewGroup, attachToRoot: Boolean) {
        binding = FragmentCreateSurveyObserversBinding.inflate(inflater, wrapper, attachToRoot)
        binding.lifecycleOwner = viewLifecycleOwner
    }

    override fun populateContentBinding() {
        binding.viewModel = viewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setSelectFieldOnCLickListener(
            skyField,
            viewModel.sky,
            Sky.enumValuesAsDisplayable(),
            R.string.skySelectTitle
        )
        setSelectFieldOnCLickListener(
            precipitationField,
            viewModel.precipitation,
            Precipitation.enumValuesAsDisplayable(),
            R.string.precipitationSelectTitle
        )
        setSelectFieldOnCLickListener(
            windDirectionField,
            viewModel.windDirection,
            CompassDirection.enumValuesAsDisplayable(),
            R.string.windDirectionSelectTitle
        )
        setSelectFieldOnCLickListener(
            windIntensityField,
            viewModel.windIntensity,
            WindIntensity.enumValuesAsDisplayable(),
            R.string.windIntensitySelectTitle
        )
        setSelectFieldOnCLickListener(
            surfField,
            viewModel.surf,
            Surf.enumValuesAsDisplayable(),
            R.string.surfSelectTitle
        )
    }
}
