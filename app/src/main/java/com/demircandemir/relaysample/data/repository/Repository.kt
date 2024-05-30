package com.demircandemir.relaysample.data.repository

import androidx.paging.PagingData
import com.demircandemir.relaysample.domain.model.MealInfo
import com.demircandemir.relaysample.domain.model.MealsResponse
import com.demircandemir.relaysample.domain.model.UserInfo
import com.demircandemir.relaysample.domain.repositories.DataStoreOperations
import com.demircandemir.relaysample.domain.repositories.LocalDataSource
import com.demircandemir.relaysample.domain.repositories.RemoteDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class Repository @Inject constructor (
    private val local: LocalDataSource,
    private val remote: RemoteDataSource,
    private val dataStore: DataStoreOperations
) {

    fun getAllMeals(): Flow<PagingData<MealInfo>> {
        return remote.getAllMeals()
    }

    fun getMealForSelection(repast: String): Flow<PagingData<MealInfo>> {
        return remote.getMealsForSelection(repast = repast)
    }

//    suspend fun getSelectedMeals(id: Int): MealsResponse {
//        return remote.getSelectedMeal(id)
//    }

    fun searchMeals(name: String): Flow<PagingData<MealInfo>> {
        return remote.searchMeals(name = name)
    }


    suspend fun saveSurveyState(completed: Boolean) {
        dataStore.saveSurveyState(completed = completed)
    }

    fun readSurveyState(): Flow<Boolean> = dataStore.readSurveyState()



//    suspend fun getUserInfo(id: String): UserInfo {
//        return local.getUserInfo(id)
//    }
//
//    suspend fun insertUserInfo(userInfo: UserInfo) {
//        return local.insertUserInfo(userInfo)
//    }


    fun postDietPlan(userId: Int, repast: String, meals: String) {
        remote.postDietPlan(userId, repast, meals)
    }

    suspend fun getUserInfo(): UserInfo {
        return remote.getUserInfo()
    }

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
    ) {
        remote.postUserInfo(
            id = id,
            name = name,
            image = image,
            goal = goal,
            weight = weight,
            height = height,
            age = age,
            gender = gender,
            exerciseDayInAWeek = exerciseDayInAWeek,
            weightGoal = weightGoal,
            timeFrame = timeFrame,
            diet_type = diet_type
        )
    }



}