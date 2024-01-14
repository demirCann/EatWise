package com.demircandemir.relaysample.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.demircandemir.relaysample.util.Constants.MEAL_REMOTE_KEYS_DATABASE

@Entity(tableName = MEAL_REMOTE_KEYS_DATABASE)
data class MealRemoteKeys(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val prevPage: Int?,
    val nextPage: Int?,
    val lastUpdated: Long? = null
)
