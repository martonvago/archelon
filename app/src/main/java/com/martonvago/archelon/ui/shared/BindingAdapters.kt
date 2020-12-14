package com.martonvago.archelon.ui.shared

import android.widget.TextView
import androidx.annotation.StringRes
import androidx.databinding.BindingAdapter
import com.martonvago.archelon.util.displayDate
import org.threeten.bp.LocalDateTime

@BindingAdapter("android:text")
fun TextView.setTextFromStringResource(@StringRes stringRes: Int) {
    text = if (stringRes != 0) context.getString(stringRes) else  ""
}

@BindingAdapter("android:text")
fun TextView.setTextFromLocalDateTime(date: LocalDateTime?) {
    text = date?.displayDate() ?: ""
}