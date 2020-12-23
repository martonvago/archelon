package com.martonvago.archelon.ui.createsurvey.placetime

import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.DatePicker
import androidx.fragment.app.DialogFragment
import com.martonvago.archelon.R
import com.martonvago.archelon.hilt.hiltNavGraphViewModels
import com.martonvago.archelon.viewmodel.CreateSurveyViewModel
import dagger.hilt.android.AndroidEntryPoint

/**
 * A dialog fragment for picking a date, which uses the native Android date picker.
 * The Calendar's month value is between 0 and 11, but LocalDate's monthValue gives values
 * from 1 to 12, so we correct for this when sending data to and from the picker.
 */
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