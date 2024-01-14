package com.demircandemir.relaysample.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.demircandemir.relaysample.data.paging_source.SearchMealsSource
import com.demircandemir.relaysample.data.remote.EatWiseApi
import com.demircandemir.relaysample.domain.model.MealInfo
import com.demircandemir.relaysample.domain.model.UserInfo
import com.demircandemir.relaysample.domain.repositories.RemoteDataSource
import kotlinx.coroutines.flow.Flow

class RemoteDataSourceImpl(
    private val eatWiseApi: EatWiseApi
): RemoteDataSource {
    override fun getUserInfo(id: String): UserInfo {
        TODO("Not yet implemented")
    }

    override fun postUserInfo(id: String) {
        TODO("Not yet implemented")
    }

    override fun getAllMeals(): Flow<PagingData<MealInfo>> {
        TODO("Not yet implemented")
    }

    override fun getSelectedMeals(id: String): MealInfo {
        TODO("Not yet implemented")
    }

    override fun searchMeals(name: String): Flow<PagingData<MealInfo>> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = {
                SearchMealsSource(eatWiseApi, name)
            }
        ).flow
    }


}