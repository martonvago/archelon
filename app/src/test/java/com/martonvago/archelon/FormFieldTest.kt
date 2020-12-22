package com.martonvago.archelon

import android.os.Build
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import com.martonvago.archelon.ui.createsurvey.FormField
import com.martonvago.archelon.ui.createsurvey.TextInputField
import dagger.hilt.android.testing.HiltTestApplication
import observeForTesting
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@Config(application = HiltTestApplication::class, sdk = [Build.VERSION_CODES.O_MR1])
@RunWith(RobolectricTestRunner::class)
class FormFieldTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Test
    fun getContentValueOrDefault_returnsDefaultValue() {
        // given
        val default = 5
        val intField = FormField(default)

        // when
        val value = intField.getContentValueOrDefault()

        // then
        assertThat(value).isEqualTo(default)
    }

    @Test
    fun setContentValue_setsNewValueToContent() {
        // given
        val newValue = 9
        val intField = FormField(5)

        // when
        intField.setContentValue(newValue)

        // then
        assertThat(intField.getContentValueOrDefault()).isEqualTo(newValue)
    }

    @Test
    fun valid_requiredAndNull_isFalse() {
        // given
        val intField = FormField<Int?>(5)

        // when
        intField.setContentValue(null)

        // then
        intField.valid.observeForTesting {
            assertThat(intField.valid.value).isFalse()
        }
    }

    @Test
    fun valid_notRequiredAndNull_isTrue() {
        // given
        val intField = FormField<Int?>(5, false)

        // when
        intField.setContentValue(null)

        // then
        intField.valid.observeForTesting {
            assertThat(intField.valid.value).isTrue()
        }
    }

    @Test
    fun valid_notRequiredAndNonNull_isTrue() {
        // given
        val intField = FormField<Int?>(5, false)

        // then
        intField.valid.observeForTesting {
            assertThat(intField.valid.value).isTrue()
        }
    }

    @Test
    fun valid_requiredAndNonNull_isTrue() {
        // given
        val intField = FormField<Int?>(5)

        // then
        intField.valid.observeForTesting {
            assertThat(intField.valid.value).isTrue()
        }
    }

    @Test
    fun textInputFieldValid_requiredAndNotEmpty_isTrue() {
        // given
        val textField = TextInputField("Test")

        // then
        textField.valid.observeForTesting {
            assertThat(textField.valid.value).isTrue()
        }
    }

    @Test
    fun textInputFieldValid_requiredAndEmpty_isFalse() {
        // given
        val textField = TextInputField("")

        // then
        textField.valid.observeForTesting {
            assertThat(textField.valid.value).isFalse()
        }
    }

    @Test
    fun textInputFieldValid_notRequiredAndEmpty_isTrue() {
        // given
        val textField = TextInputField("", false)

        // then
        textField.valid.observeForTesting {
            assertThat(textField.valid.value).isTrue()
        }
    }

    @Test
    fun textInputFieldValid_notRequiredAndNotEmpty_isTrue() {
        // given
        val textField = TextInputField("Test", false)

        // then
        textField.valid.observeForTesting {
            assertThat(textField.valid.value).isTrue()
        }
    }
}