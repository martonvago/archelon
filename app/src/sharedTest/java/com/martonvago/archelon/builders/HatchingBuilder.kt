package com.martonvago.archelon.builders

import com.martonvago.archelon.entity.Hatching

data class HatchingBuilder(
    private var surveyId: Long? = null
) {
    fun surveyId(surveyId: Long) = apply { this.surveyId = surveyId }
    fun build() = Hatching(surveyId)
}
