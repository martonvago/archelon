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
import com.martonvago.archelon.ui.shared.SelectComponent
import com.martonvago.archelon.ui.shared.setUpSelectAdapter
import kotlinx.android.synthetic.main.fragment_create_survey_observers.*

/**
 * A simple [Fragment] subclass.
 */
class CreateSurveyObserversFragment: CreateSurveyBaseFragment(
    true,
    R.id.action_createSurveyObserversFragment_to_createSurveyMenuFragment
) {
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

        val selectComponents = listOf(
            SelectComponent(viewModel.sky, R.string.sky, R.string.skySelectTitle, Sky.enumValuesAsDisplayable()),
            SelectComponent(viewModel.precipitation, R.string.precipitation, R.string.precipitationSelectTitle, Precipitation.enumValuesAsDisplayable()),
            SelectComponent(viewModel.windDirection, R.string.windDirection, R.string.windDirectionSelectTitle, CompassDirection.enumValuesAsDisplayable()),
            SelectComponent(viewModel.windIntensity, R.string.windIntensity, R.string.windIntensitySelectTitle, WindIntensity.enumValuesAsDisplayable()),
            SelectComponent(viewModel.surf, R.string.surf, R.string.surfSelectTitle, Surf.enumValuesAsDisplayable())
        )

        selectFieldsContainer.setUpSelectAdapter(selectComponents, viewLifecycleOwner) { selectArgs: SelectArgs ->
            CreateSurveyObserversFragmentDirections.actionCreateSurveyObserversFragmentToSelectBottomSheetDialogFragment(selectArgs)
        }
    }
}