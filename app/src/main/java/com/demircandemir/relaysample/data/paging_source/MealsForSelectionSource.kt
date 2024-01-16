package com.demircandemir.relaysample.data.paging_source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.demircandemir.relaysample.data.remote.EatWiseApi
import com.demircandemir.relaysample.domain.model.MealInfo
import javax.inject.Inject

class MealsForSelectionSource @Inject constructor(
    private val eatWiseApi: EatWiseApi,
    private val repast: String
): PagingSource<Int, MealInfo>() {
    override fun getRefreshKey(state: PagingState<Int, MealInfo>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MealInfo> {
        return try {
            val nextPageNumber = params.key ?: 1
            val apiResponse = eatWiseApi.getMealsForSelection(repast = repast, nextPageNumber)
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