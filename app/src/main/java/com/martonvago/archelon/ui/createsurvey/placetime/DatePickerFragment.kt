package com.martonvago.archelon.ui.createsurvey.placetime

import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.DatePicker
import androidx.fragment.app.DialogFragment
import com.martonvago.archelon.R
import com.martonvago.archelon.di.hiltNavGraphViewModels
import com.martonvago.archelon.viewmodel.CreateSurveyViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DatePickerFragment : DialogFragment(), DatePickerDialog.OnDateSetListener {

    val viewModel by hiltNavGraphViewModels<CreateSurveyViewModel>(R.id.createSurveyNavGraph)

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return DatePickerDialog(
            requireContext(),
            this,
            viewModel.dateTime.getContentValueOrDefault().year,
            viewModel.dateTime.getContentValueOrDefault().monthValue - 1,
            viewModel.dateTime.getContentValueOrDefault().dayOfMonth
            )
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, day: Int) {
        viewModel.updateDate(year, month + 1, day)
    }
}