package com.demircandemir.relaysample.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class UserResponse(
    val success: Boolean,
    val message: String? = null,
    val userInfo: UserInfo
)

@Serializable
data class MealsResponse(
    val success: Boolean,
    val message: String? = null,
    val meals: List<MealInfo>
)

@Serializable
data class MealResponse(
    val success: Boolean,
    val message: String? = null,
    val meal: MealInfo
)