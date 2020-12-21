package com.martonvago.archelon.ui.createsurvey

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.martonvago.archelon.databinding.FragmentSelectBottomSheetDialogBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_select_bottom_sheet_dialog.*

@AndroidEntryPoint
class SelectBottomSheetDialogFragment : BottomSheetDialogFragment() {

    private val args: SelectBottomSheetDialogFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentSelectBottomSheetDialogBinding.inflate(inflater, container, false)
        binding.selectTitle = args.selectContent.title
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        optionsContainer.layoutManager = LinearLayoutManager(context)
        optionsContainer.adapter = OptionsAdapter(args.selectContent, findNavController())

        closeSelect.setOnClickListener {
            findNavController().navigateUp()
        }
    }
}