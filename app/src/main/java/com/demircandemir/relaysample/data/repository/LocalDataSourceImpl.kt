package com.demircandemir.relaysample.data.repository

import com.demircandemir.relaysample.data.local.EatWiseDatabase
import com.demircandemir.relaysample.domain.model.MealInfo
import com.demircandemir.relaysample.domain.model.UserInfo
import com.demircandemir.relaysample.domain.repositories.LocalDataSource

class LocalDataSourceImpl(
    private val eatWiseDatabase: EatWiseDatabase
): LocalDataSource {

    private val mealDao = eatWiseDatabase.mealDao()

//    private val userInfoDao = eatWiseDatabase.userInfoDao()

    override suspend fun getSelectedMeal(mealId: Int): MealInfo {
        return mealDao.getSelectedMeal(mealId = mealId)
    }

//    override suspend fun getUserInfo(id: String): UserInfo {
//        return userInfoDao.getUserInfo(id)
//    }
//
//    override suspend fun insertUserInfo(userInfo: UserInfo) {
//        return userInfoDao.insertUserInfo(userInfo)
//    }


}