package com.martonvago.archelon.ui.createsurvey

import android.os.Parcelable
import com.martonvago.archelon.entity.Displayable
import kotlinx.android.parcel.Parcelize

/**
 * Passed from fragments with select input fields to select components. Currently passed from
 * create survey screen fragments to [com.martonvago.archelon.ui.shared.SelectBottomSheetDialogFragment].
 */
@Parcelize
data class SelectArgs(
    val targetField: SelectField,
    val selectOptions: List<Displayable>,
    val title: String
): Parcelable