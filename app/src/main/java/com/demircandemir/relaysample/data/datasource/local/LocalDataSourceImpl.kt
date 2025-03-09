package com.demircandemir.relaysample.data.datasource.local

import com.demircandemir.relaysample.data.local.EatWiseDatabase
import com.demircandemir.relaysample.domain.model.MealInfo

class LocalDataSourceImpl(
    eatWiseDatabase: EatWiseDatabase
): LocalDataSource {
    private val mealDao = eatWiseDatabase.mealDao()

    override suspend fun getSelectedMeal(mealId: Int): MealInfo {
        return mealDao.getSelectedMeal(mealId = mealId)
    }
}