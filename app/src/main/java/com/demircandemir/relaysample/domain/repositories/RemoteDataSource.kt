package com.demircandemir.relaysample.domain.repositories

import androidx.paging.PagingData
import com.demircandemir.relaysample.domain.model.MealInfo
import com.demircandemir.relaysample.domain.model.MealsResponse
import com.demircandemir.relaysample.domain.model.UserInfo
import kotlinx.coroutines.flow.Flow

interface RemoteDataSource {

    suspend fun getUserInfo(): UserInfo

    suspend fun postUserInfo(
        id: Int,
        name: String,
        image: String,
        goal: String,
        weight: String,
        height: String,
        age: String,
        gender: String,
        exerciseDayInAWeek: String,
        weightGoal: String,
        timeFrame: String,
        diet_type: String
    )

    fun getAllMeals(): Flow<PagingData<MealInfo>>

    fun getMealsForSelection(repast: String): Flow<PagingData<MealInfo>>

    fun getSelectedMeals(id: String): MealInfo

//    suspend fun getSelectedMeal(id: Int): MealsResponse

    fun searchMeals(name: String): Flow<PagingData<MealInfo>>


    fun postDietPlan(userId: Int, repast: String, meals: String)



}