package com.martonvago.archelon.ui.createsurvey.dialogs.select

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.martonvago.archelon.databinding.FragmentSelectBottomSheetDialogBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_select_bottom_sheet_dialog.*

/**
 * A bottom sheet dialog for displaying select options.
 */
@AndroidEntryPoint
class SelectBottomSheetDialogFragment : BottomSheetDialogFragment() {

    private val args: SelectBottomSheetDialogFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentSelectBottomSheetDialogBinding.inflate(inflater, container, false)
        binding.selectTitle = args.selectOptionArgs.title
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        optionsContainer.adapter = OptionsAdapter(args.selectOptionArgs, findNavController())

        closeSelect.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    override fun onStart() {
        super.onStart()
        // Forces the sheet to appear at max height even on landscape
        val behaviour = BottomSheetBehavior.from(requireView().parent as View)
        behaviour.state = BottomSheetBehavior.STATE_EXPANDED
    }
}