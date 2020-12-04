package com.martonvago.archelon.ui.morningsurvey

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MorningSurveyViewModel: ViewModel() {
    val test = MutableLiveData<String>().apply { value = "this is from viewmodel" }
    val empty = MutableLiveData<Boolean>().apply { value = false }
}