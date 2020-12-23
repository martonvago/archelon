package com.martonvago.archelon.ui.createsurvey.observers

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.martonvago.archelon.R
import com.martonvago.archelon.databinding.FragmentCreateSurveyObserversBinding
import com.martonvago.archelon.entity.enumValuesAsDisplayable
import com.martonvago.archelon.entity.enums.*
import com.martonvago.archelon.ui.createsurvey.CreateSurveyBaseFragment
import com.martonvago.archelon.ui.createsurvey.dialogs.select.SelectOptionArgs
import com.martonvago.archelon.ui.createsurvey.SelectFieldArgs
import com.martonvago.archelon.ui.shared.setUpSelectAdapter
import com.martonvago.archelon.ui.shared.setUpTextInputAdapter
import com.martonvago.archelon.util.asEnglishOrdinal
import kotlinx.android.synthetic.main.fragment_create_survey_observers.*

/**
 * This fragment is where the user enters the leader, observers and weather conditions.
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

        val selectFields = listOf(
            SelectFieldArgs(viewModel.sky, R.string.sky, R.string.skySelectTitle, Sky.enumValuesAsDisplayable()),
            SelectFieldArgs(viewModel.precipitation, R.string.precipitation, R.string.precipitationSelectTitle, Precipitation.enumValuesAsDisplayable()),
            SelectFieldArgs(viewModel.windDirection, R.string.windDirection, R.string.windDirectionSelectTitle, CompassDirection.enumValuesAsDisplayable()),
            SelectFieldArgs(viewModel.windIntensity, R.string.windIntensity, R.string.windIntensitySelectTitle, WindIntensity.enumValuesAsDisplayable()),
            SelectFieldArgs(viewModel.surf, R.string.surf, R.string.surfSelectTitle, Surf.enumValuesAsDisplayable())
        )

        val inputComponents = listOf(TextInputFieldArgs(viewModel.leader, resources.getString(R.string.leader))) +
                viewModel.observers.mapIndexed { i, field ->
                    // The leader counts as the first observer
                    val ordinalStr = (i + 2).asEnglishOrdinal()
                    TextInputFieldArgs(field, resources.getString(R.string.observer, ordinalStr))
                }

        inputFieldsContainer.setUpTextInputAdapter(inputComponents, viewLifecycleOwner)

        selectFieldsContainer.setUpSelectAdapter(selectFields, viewLifecycleOwner) { selectOptionArgs: SelectOptionArgs ->
            CreateSurveyObserversFragmentDirections
                .actionCreateSurveyObserversFragmentToSelectBottomSheetDialogFragment(selectOptionArgs)
        }
    }
}