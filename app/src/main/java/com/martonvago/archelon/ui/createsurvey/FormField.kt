package com.martonvago.archelon.ui.createsurvey

import android.os.Parcelable
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.martonvago.archelon.entity.Displayable
import kotlinx.android.parcel.Parcelize

open class FormField<T>(
    default: T,
    checkIfValid: (content: T) -> Boolean = {it != null}
){
    val content: MutableLiveData<T> = MutableLiveData(default)
    val valid = MediatorLiveData<Boolean>()

    init {
        valid.addSource(content) { contentValue ->
            valid.value = checkIfValid(contentValue)
        }
    }

    fun getContentValue(): T? {
        return content.value
    }

    fun setContentValue(newValue: T) {
        content.value = newValue
    }
}

/**
 * A subclass of [FormField] specifically for select fields, which we can pass in the [SelectArgs]
 * navarg between navigation components.
 */
@Parcelize
class SelectField(val default: Displayable): FormField<Displayable>(default), Parcelable