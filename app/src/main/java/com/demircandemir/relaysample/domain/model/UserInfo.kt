package com.demircandemir.relaysample.domain.model

import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
data class UserInfo(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val name: String,
    val image: String,
    val goal: String,
    val weight: Int,
    val height: Int,
    val age: Int,
    val gender: String,
    val bmr: Long,
    val exerciseDayInAWeek: Int
)
