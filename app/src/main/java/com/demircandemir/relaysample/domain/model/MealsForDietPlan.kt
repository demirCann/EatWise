package com.demircandemir.relaysample.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class MealsForDietPlan(
    val meals: List<MealInfo>
)