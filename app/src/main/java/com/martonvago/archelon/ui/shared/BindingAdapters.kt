package com.martonvago.archelon.ui.shared

import android.widget.TextView
import androidx.annotation.StringRes
import androidx.databinding.BindingAdapter

@BindingAdapter("android:text")
fun TextView.setTextFromStringResource(@StringRes stringRes: Int) {
    text = if (stringRes != 0) context.getString(stringRes) else  ""
}