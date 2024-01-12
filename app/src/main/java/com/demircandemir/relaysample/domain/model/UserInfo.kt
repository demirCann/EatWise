package com.demircandemir.relaysample.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class UserInfo(
    val id: Int,
    val name: String,
    val image: String,
    val weight: Int,
    val height: Int,
    val age: Int,
    val sex: String,
    val bmr: Long,
    val exerciseDayInAWeek: Int
)
