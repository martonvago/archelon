package com.martonvago.archelon.ui.createsurvey

import android.os.Parcelable
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.martonvago.archelon.entity.Displayable
import kotlinx.android.parcel.Parcelize

/**
 * A class representing a field on a form. Its [MutableLiveData] content can be bound to variables
 * in layout files using data binding. Its [MediatorLiveData] validity can be used to provide
 * feedback to the user and to keep track of the validity of the form as a whole, as it is done in
 * [FormViewModel].
 */
open class FormField<T>(
    private val default: T,
    private val required: Boolean = true
){
    val content: MutableLiveData<T> = MutableLiveData(default)
    val valid = MediatorLiveData<Boolean>()

    init {
        valid.addSource(content) {
            valid.value = checkIfValid()
        }
    }

    fun getContentValueOrDefault(): T {
        return content.value ?: default
    }

    fun setContentValue(newValue: T) {
        content.value = newValue
    }

    open fun checkIfValid(): Boolean {
        return !required || content.value != null
    }
}

/**
 * A subclass of [FormField] specifically for select fields, which we can pass in the [SelectArgs]
 * navarg between navigation components.
 */
@Parcelize
class SelectField(val default: Displayable): FormField<Displayable>(default), Parcelable

/**
 * A subclass of [FormField] specifically for text input fields, which we can pass to included
 * XML layouts while preserving two-way data binding.
 */
class TextInputField(default: String, private val required: Boolean = true): FormField<String>(default, required) {
    override fun checkIfValid(): Boolean {
        return !required || content.value != ""
    }
}
