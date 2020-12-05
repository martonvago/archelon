package com.martonvago.archelon.dao.local

import androidx.room.Dao
import androidx.room.Insert
import com.martonvago.archelon.entity.AdultEmergence

@Dao
interface AdultEmergenceDao {
    @Insert
    suspend fun save(adultEmergence: AdultEmergence)
}