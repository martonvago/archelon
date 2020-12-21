package com.martonvago.archelon.dao.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.martonvago.archelon.entity.AdultEmergence

@Dao
interface AdultEmergenceDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(adultEmergence: AdultEmergence): Long
}