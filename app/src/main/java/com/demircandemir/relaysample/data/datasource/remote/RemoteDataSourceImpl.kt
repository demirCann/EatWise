package com.demircandemir.relaysample.data.datasource.remote

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.demircandemir.relaysample.data.datasource.paging.GetAllMealsSource
import com.demircandemir.relaysample.data.datasource.paging.MealsForSelectionSource
import com.demircandemir.relaysample.data.datasource.paging.SearchMealsSource
import com.demircandemir.relaysample.data.remote.EatWiseApi
import com.demircandemir.relaysample.domain.model.MealInfo
import com.demircandemir.relaysample.domain.model.MealResponse
import com.demircandemir.relaysample.domain.model.MealsForDietPlan
import com.demircandemir.relaysample.domain.model.MealsRequest
import com.demircandemir.relaysample.util.ApiResult
import com.demircandemir.relaysample.util.Constants.MEAL_ITEMS_PER_PAGE
import com.demircandemir.relaysample.util.apiFlow
import kotlinx.coroutines.flow.Flow

class RemoteDataSourceImpl(
    private val eatWiseApi: EatWiseApi
): RemoteDataSource {

    override fun getAllMeals(): Flow<PagingData<MealInfo>> {
        return Pager(
            config = PagingConfig(pageSize = MEAL_ITEMS_PER_PAGE),
            pagingSourceFactory = {
                GetAllMealsSource(eatWiseApi)
            }
        ).flow
    }

    override fun getMealsForSelection(repast: String): Flow<PagingData<MealInfo>> {
        return Pager(
            config = PagingConfig(pageSize = MEAL_ITEMS_PER_PAGE),
            pagingSourceFactory = {
                MealsForSelectionSource(eatWiseApi, repast)
            }
        ).flow
    }

    override suspend fun getSelectedMeal(id: Int): Flow<ApiResult<MealResponse>> = apiFlow {
        eatWiseApi.getSelectedMeal(id)
    }

    override fun searchMeals(query: String): Flow<PagingData<MealInfo>> {
        return Pager(
            config = PagingConfig(pageSize = MEAL_ITEMS_PER_PAGE),
            pagingSourceFactory = {
                SearchMealsSource(eatWiseApi, query)
            }
        ).flow
    }

    override suspend fun getDietPlan(userId: Int, repast: String): Flow<ApiResult<MealsForDietPlan>> = apiFlow {
        eatWiseApi.getDietPlan(userId, repast)
    }

    override fun postDietPlan(userId: Int, repast: String, meals: String) {
        eatWiseApi.postDietPlan(userId, repast, meals)
    }

    override suspend fun updateDietPlan(userId: Int, repast: String, meals: MealsRequest) {
        eatWiseApi.updateDietPlan(userId, repast, meals)
    }
}