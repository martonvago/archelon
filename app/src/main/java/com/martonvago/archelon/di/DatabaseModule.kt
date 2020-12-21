package com.martonvago.archelon.di

import android.content.Context
import com.martonvago.archelon.dao.local.AdultEmergenceDao
import com.martonvago.archelon.dao.local.HatchingDao
import com.martonvago.archelon.dao.local.SurveyDao
import com.martonvago.archelon.database.local.ArchelonDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext

/**
 * Object to provide dao dependencies for the repository.
 */
@Module
@InstallIn(ApplicationComponent::class)
object DatabaseModule {

    @Provides
    fun provideSurveyDao(@ApplicationContext appContext: Context): SurveyDao {
        return ArchelonDatabase.getInstance(appContext).surveyDao
    }

    @Provides
    fun provideAdultEmergenceDao(@ApplicationContext appContext: Context): AdultEmergenceDao {
        return ArchelonDatabase.getInstance(appContext).adultEmergenceDao
    }

    @Provides
    fun provideHatchingDao(@ApplicationContext appContext: Context): HatchingDao {
        return ArchelonDatabase.getInstance(appContext).hatchingDao
    }
}