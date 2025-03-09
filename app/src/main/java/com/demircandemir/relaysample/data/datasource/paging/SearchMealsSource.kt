package com.demircandemir.relaysample.data.datasource.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.demircandemir.relaysample.data.remote.EatWiseApi
import com.demircandemir.relaysample.domain.model.MealInfo
import com.demircandemir.relaysample.util.Constants.RESOURCE_NOT_FOUND
import com.demircandemir.relaysample.util.Constants.UNAUTHORIZED_ACCESS
import javax.inject.Inject

class SearchMealsSource @Inject constructor(
    private val eatWiseApi: EatWiseApi,
    private val query: String
) : PagingSource<Int, MealInfo>() {

    override fun getRefreshKey(state: PagingState<Int, MealInfo>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MealInfo> {
        return try {
            val apiResponse = eatWiseApi.searchMeals(name = query)
            when {
                apiResponse.isSuccessful -> {
                    val mealListResponse = apiResponse.body()
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
                apiResponse.code() == 401 -> {
                    LoadResult.Error(Exception(UNAUTHORIZED_ACCESS))
                }
                apiResponse.code() == 404 -> {
                    LoadResult.Error(Exception(RESOURCE_NOT_FOUND))
                }
                else -> {
                    LoadResult.Error(Exception(apiResponse.message()))
                }
            }
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}