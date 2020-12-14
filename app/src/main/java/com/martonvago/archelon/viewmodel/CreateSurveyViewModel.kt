package com.martonvago.archelon.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.martonvago.archelon.entity.AdultEmergence
import com.martonvago.archelon.entity.Hatching
import com.martonvago.archelon.entity.Survey
import com.martonvago.archelon.entity.SurveyWithEvents
import com.martonvago.archelon.entity.enums.*
import com.martonvago.archelon.repository.ArchelonRepository
import com.martonvago.archelon.ui.createsurvey.FormField
import com.martonvago.archelon.ui.createsurvey.SelectField
import com.martonvago.archelon.ui.createsurvey.TextInputField
import com.martonvago.archelon.util.atDate
import com.martonvago.archelon.util.atTime
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.threeten.bp.LocalDateTime


class CreateSurveyViewModel @ViewModelInject constructor(
    private val archelonRepository: ArchelonRepository
): ViewModel() {

    val dateTime: FormField<LocalDateTime> = FormField(LocalDateTime.now())
    val leader: TextInputField = TextInputField("")
    val observers = (0 until 3).map { TextInputField("") }
    val beach = SelectField(Beach.MAVROVOUNI)
    val beachSector = SelectField(CompassDirection.EAST)
    val sky = SelectField(Sky.SUNNY)
    val precipitation = SelectField(Precipitation.NONE)
    val windIntensity = SelectField(WindIntensity.CALM)
    val windDirection = SelectField(CompassDirection.EAST)
    val surf = SelectField(Surf.CALM)

    private val adultEmergenceEvents: MutableList<AdultEmergence> = mutableListOf()
    private val hatchingEvents: MutableList<Hatching> = mutableListOf()

    private val formValid = MediatorLiveData<Boolean>()

    init {
        val fields = listOf<FormField<*>>(beach, dateTime, leader)
        fields.forEach { field ->
            formValid.addSource(field.valid) {
                formValid.value = fields
                    .map { field -> field.valid.value }
                    .reduce { otherFieldsValid, fieldValid -> otherFieldsValid!!.and(fieldValid as Boolean) }
            }
        }
    }

    fun updateTime(hour: Int, minute: Int) {
        val oldDate = dateTime.getContentValue()
        dateTime.setContentValue(oldDate.atTime(hour, minute))
    }

    fun updateDate(year: Int, month: Int, day: Int) {
        val oldDate = dateTime.getContentValue()
        dateTime.setContentValue(oldDate.atDate(year, month, day))
    }

    fun addAdultEmergence() {
        val adultEmergence = AdultEmergence()
        adultEmergenceEvents.add(adultEmergence)
    }

    fun addHatching() {
        val hatching = Hatching()
        hatchingEvents.add(hatching)
    }

    fun submitSurvey() {
        val survey = Survey(
            dateTime.getContentValue(),
            beach.getContentValue() as Beach,
            beachSector.getContentValue() as CompassDirection,
            sky.getContentValue() as Sky,
            precipitation.getContentValue() as Precipitation,
            windIntensity.getContentValue() as WindIntensity,
            windDirection.getContentValue() as CompassDirection,
            surf.getContentValue() as Surf,
            leader.getContentValue(),
            observers.map { it.getContentValue() }
        )
        val surveyWithEvents = SurveyWithEvents(
            survey,
            adultEmergenceEvents,
            hatchingEvents
        )

        GlobalScope.launch {
            archelonRepository.saveSurveyWithEvents(surveyWithEvents)
        }
    }
}