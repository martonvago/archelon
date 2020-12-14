package com.martonvago.archelon.dao.local

import androidx.room.Dao
import androidx.room.Insert
import com.martonvago.archelon.entity.Hatching

@Dao
interface HatchingDao {
    @Insert
    suspend fun save(hatching: Hatching)
}