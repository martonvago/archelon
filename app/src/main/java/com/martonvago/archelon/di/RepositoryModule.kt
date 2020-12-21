package com.martonvago.archelon.di

import com.martonvago.archelon.dao.local.AdultEmergenceDao
import com.martonvago.archelon.dao.local.HatchingDao
import com.martonvago.archelon.dao.local.SurveyDao
import com.martonvago.archelon.repository.ArchelonRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

/**
 * Object to provide repository dependency for viewmodels.
 */
@Module
@InstallIn(ApplicationComponent::class)
object RepositoryModule {

    @Provides
    fun provideArchelonRepository(
        surveyDao: SurveyDao,
        adultEmergenceDao: AdultEmergenceDao,
        hatchingDao: HatchingDao
    ) = ArchelonRepository(
        surveyDao,
        adultEmergenceDao,
        hatchingDao
    )
}