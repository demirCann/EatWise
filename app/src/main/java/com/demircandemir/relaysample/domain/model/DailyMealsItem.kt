package com.demircandemir.relaysample.domain.model

data class DailyMealsItem(
    val name: String,
    val recommendedCalorie: String,
    val imageRes: Int,
    val route: String,
    val meals: List<MealInfo>
)