package com.demircandemir.relaysample.domain.model

data class DailyMeals(
    val breakfast: MealsForDietPlan,
    val lunch: MealsForDietPlan,
    val dinner: MealsForDietPlan,
    val snacks: MealsForDietPlan
) 