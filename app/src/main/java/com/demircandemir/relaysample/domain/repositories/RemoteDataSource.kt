package com.demircandemir.relaysample.domain.repositories

import androidx.paging.PagingData
import com.demircandemir.relaysample.domain.model.MealInfo
import com.demircandemir.relaysample.domain.model.UserInfo
import kotlinx.coroutines.flow.Flow

interface RemoteDataSource {

    fun getUserInfo(id: String): UserInfo

    fun postUserInfo(id: String)

    fun getAllMeals(): Flow<PagingData<MealInfo>>

    fun getSelectedMeals(id: String): MealInfo

    fun searchMeals(name: String): Flow<PagingData<MealInfo>>


}