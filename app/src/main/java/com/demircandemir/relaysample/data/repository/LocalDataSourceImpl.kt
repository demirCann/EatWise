package com.demircandemir.relaysample.data.repository

import com.demircandemir.relaysample.data.local.EatWiseDatabase
import com.demircandemir.relaysample.domain.model.MealInfo
import com.demircandemir.relaysample.domain.repositories.LocalDataSource

class LocalDataSourceImpl(
    private val eatWiseDatabase: EatWiseDatabase
): LocalDataSource {

    private val mealDao = eatWiseDatabase.mealDao()
    override suspend fun getSelectedMeal(mealId: Int): MealInfo {
        return mealDao.getSelectedMeal(mealId = mealId)
    }


}