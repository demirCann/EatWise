package com.demircandemir.relaysample.data.paging_source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.demircandemir.relaysample.data.remote.EatWiseApi
import com.demircandemir.relaysample.domain.model.MealInfo
import javax.inject.Inject

class SearchMealsSource @Inject constructor(
    private val eatWiseApi: EatWiseApi,
    private val query: String
): PagingSource<Int, MealInfo>() {
    override fun getRefreshKey(state: PagingState<Int, MealInfo>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MealInfo> {
        return try {
            val apiResponse = eatWiseApi.searchMeals(name = query)
            val meals = apiResponse.meals
            if (meals.isNotEmpty()) {
                LoadResult.Page(
                    data = meals,
                    prevKey = apiResponse.prevPage,
                    nextKey = apiResponse.nextPage
                )
            } else {
                LoadResult.Page(
                    data = emptyList(),
                    prevKey = null,
                    nextKey = null
                )
            }
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

}