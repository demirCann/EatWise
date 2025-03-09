package com.demircandemir.relaysample.data.datasource.local

import com.demircandemir.relaysample.domain.model.MealInfo

interface LocalDataSource {

    suspend fun getSelectedMeal(mealId: Int): MealInfo

//    suspend fun getUserInfo(id: String): UserInfo
//
//    suspend fun insertUserInfo(userInfo: UserInfo)

}