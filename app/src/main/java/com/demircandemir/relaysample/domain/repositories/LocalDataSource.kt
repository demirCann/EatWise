package com.demircandemir.relaysample.domain.repositories

import com.demircandemir.relaysample.domain.model.MealInfo
import com.demircandemir.relaysample.domain.model.UserInfo

interface LocalDataSource {

    suspend fun getSelectedMeal(mealId: Int): MealInfo

//    suspend fun getUserInfo(id: String): UserInfo
//
//    suspend fun insertUserInfo(userInfo: UserInfo)

}