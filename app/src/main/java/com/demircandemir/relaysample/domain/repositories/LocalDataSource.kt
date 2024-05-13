package com.demircandemir.relaysample.domain.repositories

import com.demircandemir.relaysample.domain.model.MealInfo

fun interface LocalDataSource {

    suspend fun getSelectedMeal(mealId: Int): MealInfo
}