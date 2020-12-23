package com.martonvago.archelon.ui.createsurvey.dialogs.select

import android.os.Parcelable
import androidx.annotation.StringRes
import com.martonvago.archelon.entity.Displayable
import com.martonvago.archelon.ui.createsurvey.SelectField
import kotlinx.android.parcel.Parcelize

/**
 * When a select input field is clicked, [SelectBottomSheetDialogFragment] is opened with the
 * appropriate select options. This class is used to pass data from the fragment with the
 * select field to [SelectBottomSheetDialogFragment].
 * Currently this is done in [com.martonvago.archelon.ui.createsurvey.SelectFieldsAdapter].
 */
@Parcelize
data class SelectOptionArgs(
    val targetField: SelectField,
    val selectOptions: List<Displayable>,
   @StringRes val title: Int
): Parcelable