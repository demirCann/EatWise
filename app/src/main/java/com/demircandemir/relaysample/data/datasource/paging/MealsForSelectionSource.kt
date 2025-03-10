package com.demircandemir.relaysample.data.datasource.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.demircandemir.relaysample.data.remote.EatWiseApi
import com.demircandemir.relaysample.domain.model.MealInfo
import com.demircandemir.relaysample.util.Constants.API_CALL_FAILED
import com.demircandemir.relaysample.util.Constants.RESOURCE_NOT_FOUND
import com.demircandemir.relaysample.util.Constants.UNAUTHORIZED_ACCESS
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
            val response = eatWiseApi.getMealsForSelection(repast = repast, page = nextPageNumber)

            when {
                response.isSuccessful -> {
                    val mealListResponse = response.body()
                    if (mealListResponse != null && mealListResponse.meals.isNotEmpty()) {
                        LoadResult.Page(
                            data = mealListResponse.meals,
                            prevKey = mealListResponse.prevPage,
                            nextKey = mealListResponse.nextPage
                        )
                    } else {
                        LoadResult.Page(
                            data = emptyList(),
                            prevKey = null,
                            nextKey = null
                        )
                    }
                }
                response.code() == 401 -> {
                    LoadResult.Error(Exception(UNAUTHORIZED_ACCESS))
                }
                response.code() == 404 -> {
                    LoadResult.Error(Exception(RESOURCE_NOT_FOUND))
                }
                else -> {
                    LoadResult.Error(Exception(API_CALL_FAILED + response.code()))
                }
            }
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}