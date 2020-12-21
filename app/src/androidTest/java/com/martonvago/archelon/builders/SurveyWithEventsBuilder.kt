package com.martonvago.archelon.builders

import com.martonvago.archelon.entity.AdultEmergence
import com.martonvago.archelon.entity.Hatching
import com.martonvago.archelon.entity.Survey
import com.martonvago.archelon.entity.SurveyWithEvents

data class SurveyWithEventsBuilder(
    private var adultEmergence: List<AdultEmergence> = emptyList(),
    private var hatching: List<Hatching> = emptyList()
) {
    private val surveyBuilder = SurveyBuilder()
    private val adultEmergenceBuilder = AdultEmergenceBuilder()
    private val hatchingBuilder = HatchingBuilder()
    private var survey: Survey = surveyBuilder.build()

    fun survey(survey: Survey) = apply { this.survey = survey }
    fun adultEmergence(adultEmergence: List<AdultEmergence>) = apply { this.adultEmergence = adultEmergence }
    fun hatching(hatching: List<Hatching>) = apply { this.hatching = hatching }
    fun withEvents() = apply {
        this.adultEmergence((0 until 5).map { adultEmergenceBuilder.build() })
        this.hatching((0 until 3).map { hatchingBuilder.build() })
    }

    fun build() = SurveyWithEvents(survey, adultEmergence, hatching)
}
