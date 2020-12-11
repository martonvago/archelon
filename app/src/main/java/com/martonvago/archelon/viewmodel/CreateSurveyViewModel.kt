package com.martonvago.archelon.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.martonvago.archelon.entity.Survey
import com.martonvago.archelon.entity.enums.*
import com.martonvago.archelon.repository.ArchelonRepository
import com.martonvago.archelon.ui.createsurvey.FormField
import com.martonvago.archelon.ui.createsurvey.SelectField
import com.martonvago.archelon.ui.createsurvey.TextInputField
import com.martonvago.archelon.util.atDate
import com.martonvago.archelon.util.atTime
import kotlinx.coroutines.launch
import org.threeten.bp.LocalDateTime


class CreateSurveyViewModel @ViewModelInject constructor(
    private val archelonRepository: ArchelonRepository
): ViewModel() {

    val dateTime: FormField<LocalDateTime> = FormField(LocalDateTime.now())
    val leader: TextInputField = TextInputField("")
    val observer1: TextInputField = TextInputField("")
    val observer2: TextInputField = TextInputField("")
    val beach = SelectField(Beach.MAVROVOUNI)
    val beachSector = SelectField(CompassDirection.EAST)
    val sky = SelectField(Sky.SUNNY)
    val precipitation = SelectField(Precipitation.NONE)
    val windIntensity = SelectField(WindIntensity.CALM)
    val windDirection = SelectField(CompassDirection.EAST)
    val surf = SelectField(Surf.CALM)

    val formValid = MediatorLiveData<Boolean>()

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
        dateTime.setContentValue(oldDate!!.atTime(hour, minute))
    }

    fun updateDate(year: Int, month: Int, day: Int) {
        val oldDate = dateTime.getContentValue()
        dateTime.setContentValue(oldDate!!.atDate(year, month, day))
    }

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
                "",
                emptyList()
            )
            archelonRepository.saveSurvey(survey)
        }
    }
}