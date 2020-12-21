package com.martonvago.archelon.dao.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.martonvago.archelon.entity.Hatching

@Dao
interface HatchingDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(hatching: Hatching): Long
}