package com.martonvago.archelon.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.martonvago.archelon.entity.*
import com.martonvago.archelon.repository.ArchelonRepository
import kotlinx.coroutines.launch
import org.threeten.bp.LocalDateTime

class CreateSurveyViewModel @ViewModelInject constructor(
    private val archelonRepository: ArchelonRepository
): ViewModel() {

    fun submitSurvey() {
        viewModelScope.launch {
            val survey = Survey(
                LocalDateTime.now(),
                Beach.MAVROVOUNI,
                CompassDirection.EAST,
                Sky.CLOUDY,
                Precipitation.HAIL,
                WindIntensity.CALM,
                CompassDirection.NORTH,
                Surf.MEDIUM,
                "Mr Bean",
                emptyList()
            )
            archelonRepository.saveSurvey(survey)
        }
    }

    fun dumpSurveyData() {
        viewModelScope.launch {
            val s = archelonRepository.getAllSurveys()
            println(s.value.toString())
        }
    }
}