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

    // Not using live data here as info flow is user > system
    // Would be an idea to do something like that to turn this into an edit or create sequence
    var leader = "Hello"

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
                leader,
                emptyList()
            )
            archelonRepository.saveSurvey(survey)
        }
    }
}