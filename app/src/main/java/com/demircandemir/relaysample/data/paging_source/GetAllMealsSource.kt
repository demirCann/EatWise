package com.demircandemir.relaysample.data.paging_source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.demircandemir.relaysample.data.remote.EatWiseApi
import com.demircandemir.relaysample.domain.model.MealInfo
import javax.inject.Inject

class GetAllMealsSource @Inject constructor(
    private val eatWiseApi: EatWiseApi,
): PagingSource<Int, MealInfo>(){
    override fun getRefreshKey(state: PagingState<Int, MealInfo>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: PagingSource.LoadParams<Int>): PagingSource.LoadResult<Int, MealInfo> {
        return try {
            val nextPageNumber = params.key ?: 1
            val apiResponse = eatWiseApi.getAllMeals(nextPageNumber)
            val meals = apiResponse.meals
            if (meals.isNotEmpty()) {
                PagingSource.LoadResult.Page(
                    data = meals,
                    prevKey = apiResponse.prevPage,
                    nextKey = apiResponse.nextPage
                )
            } else {
                PagingSource.LoadResult.Page(
                    data = emptyList(),
                    prevKey = null,
                    nextKey = null
                )
            }
        } catch (e: Exception) {
            PagingSource.LoadResult.Error(e)
        }
    }
}