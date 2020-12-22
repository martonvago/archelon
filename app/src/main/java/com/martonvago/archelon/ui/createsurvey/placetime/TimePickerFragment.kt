package com.martonvago.archelon.ui.createsurvey.placetime

import android.app.Dialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.text.format.DateFormat.is24HourFormat
import android.widget.TimePicker
import androidx.fragment.app.DialogFragment
import com.martonvago.archelon.R
import com.martonvago.archelon.di.hiltNavGraphViewModels
import com.martonvago.archelon.viewmodel.CreateSurveyViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TimePickerFragment : DialogFragment(), TimePickerDialog.OnTimeSetListener {

    val viewModel by hiltNavGraphViewModels<CreateSurveyViewModel>(R.id.createSurveyNavGraph)

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return TimePickerDialog(
            activity,
            this,
            viewModel.dateTime.getContentValueOrDefault().hour,
            viewModel.dateTime.getContentValueOrDefault().minute,
            is24HourFormat(activity)
        )
    }

    override fun onTimeSet(view: TimePicker, hourOfDay: Int, minute: Int) {
        viewModel.updateTime(hourOfDay, minute)
    }
}