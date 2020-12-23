package com.martonvago.archelon.ui.createsurvey

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel

/**
 * An abstract viewmodel for keeping track of the validity of a set of [FormField]s.
 */
abstract class FormViewModel: ViewModel() {
    val formValid = MediatorLiveData<Boolean>()

    // Whenever the validity of a field changes we recompute the validity of the whole form
    // by ANDing together all of their validity values
    fun observeFieldValidity(formFields: List<FormField<*>>) {
        formFields.forEach { field ->
            formValid.addSource(field.valid) {
                formValid.value = formFields
                    .map { field -> field.valid.value }
                    .reduce { acc, fieldValid ->
                        acc ?: false && fieldValid ?: false
                    }
            }
        }
    }
}