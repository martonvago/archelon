package com.martonvago.archelon.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.martonvago.archelon.entity.SurveyWithEvents
import com.martonvago.archelon.repository.ArchelonRepository
import kotlinx.coroutines.launch

class ViewSurveysViewModel @ViewModelInject constructor(
    private val archelonRepository: ArchelonRepository
): ViewModel() {

    lateinit var surveys: LiveData<List<SurveyWithEvents>>

    fun fetchSurveys() {
        viewModelScope.launch {
            surveys = archelonRepository.getAllSurveys()
        }
    }
}