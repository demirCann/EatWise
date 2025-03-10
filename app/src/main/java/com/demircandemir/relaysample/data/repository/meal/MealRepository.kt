package com.demircandemir.relaysample.data.repository.meal

import androidx.paging.PagingData
import com.demircandemir.relaysample.domain.model.MealInfo
import com.demircandemir.relaysample.domain.model.MealResponse
import com.demircandemir.relaysample.domain.model.MealsForDietPlan
import com.demircandemir.relaysample.domain.model.MealsRequest
import com.demircandemir.relaysample.util.ApiResult
import kotlinx.coroutines.flow.Flow

interface MealRepository {
    fun getAllMeals(): Flow<PagingData<MealInfo>>
    fun getMealsForSelection(repast: String): Flow<PagingData<MealInfo>>
    suspend fun getSelectedMeal(mealId: Int): Flow<ApiResult<MealResponse>>
    fun searchMeals(query: String): Flow<PagingData<MealInfo>>
    suspend fun getDietPlan(userId: Int, repast: String): Flow<ApiResult<MealsForDietPlan>>
    suspend fun saveSurveyState(completed: Boolean)
    fun readSurveyState(): Flow<Boolean>
    fun postDietPlan(userId: Int, repast: String, meals: String)
    suspend fun updateDietPlan(userId: Int, repast: String, meals: MealsRequest)
}