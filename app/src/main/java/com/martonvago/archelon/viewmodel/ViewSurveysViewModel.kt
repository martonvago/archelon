package com.martonvago.archelon.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.martonvago.archelon.entity.SurveyWithEvents
import com.martonvago.archelon.repository.ArchelonRepository

/**
 * This is the viewmodel scoped to main_nav_graph. It is responsible for keeping an up-to-date
 * [LiveData] list of surveys in the database.
 */
class ViewSurveysViewModel @ViewModelInject constructor(
    private val archelonRepository: ArchelonRepository
): ViewModel() {

    lateinit var surveys: LiveData<List<SurveyWithEvents>>

    init {
        fetchSurveys()
    }

    private fun fetchSurveys() {
        surveys = archelonRepository.getAllSurveys()
    }
}