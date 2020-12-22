package com.martonvago.archelon.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import org.threeten.bp.Clock

/**
 * Object to provide clock dependency for viewmodels.
 */
@Module
@InstallIn(ApplicationComponent::class)
object ClockModule {

    @Provides
    fun provideClock(): Clock = Clock.systemDefaultZone()
}