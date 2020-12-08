package com.martonvago.archelon.ui.createsurvey

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData

class FormField<T>(
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