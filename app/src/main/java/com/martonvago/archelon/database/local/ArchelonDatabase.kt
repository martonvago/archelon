package com.martonvago.archelon.database.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.martonvago.archelon.dao.local.AdultEmergenceDao
import com.martonvago.archelon.dao.local.SurveyDao
import com.martonvago.archelon.entity.AdultEmergence
import com.martonvago.archelon.entity.Survey

@Database(entities = [Survey::class, AdultEmergence::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class ArchelonDatabase: RoomDatabase() {

    abstract val surveyDao: SurveyDao
    abstract val adultEmergenceDao: AdultEmergenceDao

    companion object {
        @Volatile
        private var INSTANCE: ArchelonDatabase? = null

        fun getInstance(context: Context): ArchelonDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        ArchelonDatabase::class.java,
                        "archelon_database"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }

                return instance
            }
        }
    }
}