package com.martonvago.archelon.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.martonvago.archelon.repository.ArchelonRepository

class MorningSurveyViewModel @ViewModelInject constructor(
    private val archelonRepository: ArchelonRepository
): ViewModel(), LifecycleObserver {
    val test = MutableLiveData<String>().apply { value = "this is from viewmodel" }
    val empty = MutableLiveData<Boolean>().apply { value = false }
}