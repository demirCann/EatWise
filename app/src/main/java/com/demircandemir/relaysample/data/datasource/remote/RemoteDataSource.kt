package com.demircandemir.relaysample.data.datasource.remote

import androidx.paging.PagingData
import com.demircandemir.relaysample.domain.model.MealInfo
import com.demircandemir.relaysample.domain.model.MealResponse
import com.demircandemir.relaysample.domain.model.MealsForDietPlan
import com.demircandemir.relaysample.domain.model.MealsRequest
import com.demircandemir.relaysample.util.ApiResult
import kotlinx.coroutines.flow.Flow

interface RemoteDataSource {
    fun getAllMeals(): Flow<PagingData<MealInfo>>
    fun getMealsForSelection(repast: String): Flow<PagingData<MealInfo>>
    suspend fun getSelectedMeal(id: Int): Flow<ApiResult<MealResponse>>
    fun searchMeals(query: String): Flow<PagingData<MealInfo>>
    suspend fun getDietPlan(userId: Int, repast: String): Flow<ApiResult<MealsForDietPlan>>
    fun postDietPlan(userId: Int, repast: String, meals: String)
    suspend fun updateDietPlan(userId: Int, repast: String, meals: MealsRequest)
}