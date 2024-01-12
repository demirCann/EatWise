package com.demircandemir.relaysample.data.repository

import androidx.paging.PagingData
import com.demircandemir.relaysample.domain.model.MealInfo
import com.demircandemir.relaysample.domain.model.UserInfo
import com.demircandemir.relaysample.domain.repositories.DataStoreOperations
import com.demircandemir.relaysample.domain.repositories.RemoteDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class Repository @Inject constructor (
    private val remote: RemoteDataSource,
    private val dataStore: DataStoreOperations
) {



    fun getUserInfo(id: String): UserInfo {
        return remote.getUserInfo(id = id)
    }

    fun postUserInfo(id: String) {
        remote.postUserInfo(id = id)
    }

    fun getAllMeals(): Flow<PagingData<MealInfo>> {
        return remote.getAllMeals()
    }

    fun getSelectedMeals(id: String): MealInfo {
        return remote.getSelectedMeals(id = id)
    }

    fun searchMeals(name: String): Flow<PagingData<MealInfo>> {
        return remote.searchMeals(name = name)
    }


    suspend fun saveSurveyState(completed: Boolean) {
        dataStore.saveSurveyState(completed = completed)
    }

    fun readSurveyState(): Flow<Boolean> = dataStore.readSurveyState()
}