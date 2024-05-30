package com.demircandemir.relaysample.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.demircandemir.relaysample.data.local.EatWiseDatabase
import com.demircandemir.relaysample.data.paging_source.GetAllMealsSource
import com.demircandemir.relaysample.data.paging_source.MealRemoteMediator
import com.demircandemir.relaysample.data.paging_source.MealsForSelectionSource
import com.demircandemir.relaysample.data.paging_source.SearchMealsSource
import com.demircandemir.relaysample.data.remote.EatWiseApi
import com.demircandemir.relaysample.domain.model.MealInfo
import com.demircandemir.relaysample.domain.model.MealsResponse
import com.demircandemir.relaysample.domain.model.UserInfo
import com.demircandemir.relaysample.domain.repositories.RemoteDataSource
import com.demircandemir.relaysample.util.Constants.MEAL_NUMBER_PER_PAGE
import kotlinx.coroutines.flow.Flow

@OptIn(ExperimentalPagingApi::class)
class RemoteDataSourceImpl(
    private val eatWiseApi: EatWiseApi,
    private val eatWiseDatabase: EatWiseDatabase
): RemoteDataSource {

    private val mealDao = eatWiseDatabase.mealDao()

    override suspend fun getUserInfo(): UserInfo {
        return eatWiseApi.getUserInfo()
    }

    override suspend fun postUserInfo(
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
    ) {
        eatWiseApi.postUserInfo(id, name, goal, weight, height, age, gender, exerciseDayInAWeek, weightGoal, timeFrame, diet_type)
    }


    override fun getAllMeals(): Flow<PagingData<MealInfo>> {
        return Pager(
            config = PagingConfig(pageSize = MEAL_NUMBER_PER_PAGE),
            pagingSourceFactory = {
                GetAllMealsSource(eatWiseApi)
            }
        ).flow
    }


    override fun getMealsForSelection(repast: String): Flow<PagingData<MealInfo>>{
        return Pager(
            config = PagingConfig(pageSize = MEAL_NUMBER_PER_PAGE),
            pagingSourceFactory = {
                MealsForSelectionSource(eatWiseApi, repast)
            }
        ).flow
    }



    override fun getSelectedMeals(id: String): MealInfo {
        TODO("Not yet implemented")
    }

//    override suspend fun getSelectedMeal(id: Int): MealsResponse {
//        return eatWiseApi.getSelectedMeal(id)
//    }


    override fun searchMeals(name: String): Flow<PagingData<MealInfo>> {
        return Pager(
            config = PagingConfig(pageSize = MEAL_NUMBER_PER_PAGE),
            pagingSourceFactory = {
                SearchMealsSource(eatWiseApi, name)
            }
        ).flow
    }

    override fun postDietPlan(userId: Int, repast: String, meals: String) {
        eatWiseApi.postDietPlan(userId, repast, meals)
    }
}