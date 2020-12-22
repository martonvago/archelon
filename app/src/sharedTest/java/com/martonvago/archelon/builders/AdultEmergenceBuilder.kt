package com.martonvago.archelon.builders

import com.martonvago.archelon.entity.AdultEmergence

data class AdultEmergenceBuilder(
    private var surveyId: Long? = null
) {
    fun surveyId(surveyId: Long) = apply { this.surveyId = surveyId }
    fun build() = AdultEmergence(surveyId)
}
