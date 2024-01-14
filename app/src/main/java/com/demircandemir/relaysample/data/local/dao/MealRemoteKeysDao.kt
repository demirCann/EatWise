package com.demircandemir.relaysample.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.demircandemir.relaysample.domain.model.MealRemoteKeys

@Dao
interface MealRemoteKeysDao {
    @Query("SELECT * FROM meal_remote_keys_table WHERE id = :id")
    suspend fun getRemoteKeys(id: Int): MealRemoteKeys?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAllRemoteKeys(heroRemoteKeys: List<MealRemoteKeys>)

    @Query("DELETE FROM meal_remote_keys_table")
    suspend fun deleteAllRemoteKeys()
}