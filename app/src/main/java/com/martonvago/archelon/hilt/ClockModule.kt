package com.martonvago.archelon.hilt

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import org.threeten.bp.Clock

/**
 * Object to provide [Clock] dependency for viewmodels. This makes it easier to test time-dependent
 * functionality.
 */
@Module
@InstallIn(ApplicationComponent::class)
object ClockModule {

    @Provides
    fun provideClock(): Clock = Clock.systemDefaultZone()
}