package com.martonvago.archelon.ui.shared

import android.view.View
import androidx.annotation.StringRes
import androidx.navigation.NavDirections
import com.martonvago.archelon.entity.Displayable
import com.martonvago.archelon.ui.createsurvey.SelectArgs
import com.martonvago.archelon.ui.createsurvey.SelectField

interface WithSelectField {
    val navActionToSelectDialog: (SelectArgs) -> NavDirections

    fun setSelectFieldOnCLickListener(
        view: View,
        selectField: SelectField,
        options: List<Displayable>,
        @StringRes title: Int,
    ) {
        val args = SelectArgs(selectField, options, title)
        view.setNavigateOnClickListener(navActionToSelectDialog(args))
    }
}
