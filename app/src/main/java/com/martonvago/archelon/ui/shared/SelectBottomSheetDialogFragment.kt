package com.martonvago.archelon.ui.shared

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.martonvago.archelon.R
import com.martonvago.archelon.databinding.FragmentSelectBottomSheetDialogBinding
import com.martonvago.archelon.di.hiltNavGraphViewModels
import com.martonvago.archelon.entity.enums.Beach
import com.martonvago.archelon.viewmodel.CreateSurveyViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_select_bottom_sheet_dialog.*

@AndroidEntryPoint
class SelectBottomSheetDialogFragment : BottomSheetDialogFragment() {

    val viewModel by hiltNavGraphViewModels<CreateSurveyViewModel>(R.id.createSurveyNavGraph)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentSelectBottomSheetDialogBinding.inflate(inflater, container, false)
        binding.selectTitle = "Choose beach"
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val optionsAdapter: OptionsAdapter<Beach> = OptionsAdapter(enumValues()) {
            viewModel.beach.setContentValue(it as Beach)
            findNavController().navigateUp()
        }
        optionsContainer.layoutManager = LinearLayoutManager(context)
        optionsContainer.adapter = optionsAdapter

        closeSelect.setOnClickListener {
            findNavController().navigateUp()
        }
    }
}