package com.demircandemir.relaysample.data.repository.meal

import androidx.paging.PagingData
import com.demircandemir.relaysample.data.datasource.data_store.DataStoreSource
import com.demircandemir.relaysample.data.datasource.remote.RemoteDataSource
import com.demircandemir.relaysample.domain.model.MealInfo
import com.demircandemir.relaysample.domain.model.MealResponse
import com.demircandemir.relaysample.domain.model.MealsForDietPlan
import com.demircandemir.relaysample.domain.model.MealsRequest
import com.demircandemir.relaysample.util.ApiResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MealRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val dataStore: DataStoreSource
) : MealRepository {
    override fun getAllMeals(): Flow<PagingData<MealInfo>> {
        return remoteDataSource.getAllMeals()
    }

    override fun getMealsForSelection(repast: String): Flow<PagingData<MealInfo>> {
        return remoteDataSource.getMealsForSelection(repast)
    }

    override suspend fun getSelectedMeal(mealId: Int): Flow<ApiResult<MealResponse>> {
        return remoteDataSource.getSelectedMeal(mealId)
    }

    override fun searchMeals(query: String): Flow<PagingData<MealInfo>> {
        return remoteDataSource.searchMeals(query)
    }

    override suspend fun getDietPlan(
        userId: Int,
        repast: String
    ): Flow<ApiResult<MealsForDietPlan>> {
        return remoteDataSource.getDietPlan(userId, repast)
    }

    override suspend fun saveSurveyState(completed: Boolean) {
        dataStore.saveSurveyState(completed)
    }

    override fun readSurveyState(): Flow<Boolean> {
        return dataStore.readSurveyState()
    }

    override fun postDietPlan(userId: Int, repast: String, meals: String) {
        remoteDataSource.postDietPlan(userId, repast, meals)
    }

    override suspend fun updateDietPlan(userId: Int, repast: String, meals: MealsRequest) {
        remoteDataSource.updateDietPlan(userId, repast, meals)
    }
}