package com.demircandemir.relaysample.data.paging_source

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.demircandemir.relaysample.data.local.EatWiseDatabase
import com.demircandemir.relaysample.data.remote.EatWiseApi
import com.demircandemir.relaysample.domain.model.MealInfo
import com.demircandemir.relaysample.domain.model.MealRemoteKeys
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class MealRemoteMediator @Inject constructor(
    private val eatWiseApi: EatWiseApi,
    private val eatWiseDatabase: EatWiseDatabase
): RemoteMediator<Int, MealInfo>() {

    private val mealDao = eatWiseDatabase.mealDao()
    private val mealRemoteKeysDao = eatWiseDatabase.mealRemoteKeysDao()

    override suspend fun initialize(): InitializeAction {
        val currentTime = System.currentTimeMillis()
        val lastUpdated = mealRemoteKeysDao.getRemoteKeys(id = 1)?.lastUpdated ?: 0L
        val cacheTimeout = 1440

        val diffInMinutes = (currentTime - lastUpdated) / 1000 / 60
        return if( diffInMinutes.toInt() <= cacheTimeout) {
            InitializeAction.SKIP_INITIAL_REFRESH
        } else {
            InitializeAction.LAUNCH_INITIAL_REFRESH
        }

    }

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, MealInfo>
    ): MediatorResult {
        return try {
            val page = when(loadType) {
                LoadType.REFRESH -> {
                    val remoteKeys = getRemoteKeyClosestCurrentPosition(state)
                    remoteKeys?.nextPage?.minus(1) ?: 1
                }
                LoadType.PREPEND -> {
                    val remoteKeys = getRemoteKeyForFirstItem(state)
                    val prevPage = remoteKeys?.prevPage
                        ?: return MediatorResult.Success(
                            endOfPaginationReached = remoteKeys != null
                        )
                    prevPage
                }
                LoadType.APPEND -> {
                    val remoteKeys = getRemoteKeyForLastItem(state)
                    val nextPage = remoteKeys?.nextPage
                        ?: return MediatorResult.Success(
                            endOfPaginationReached = remoteKeys != null
                        )
                    nextPage
                }
            }

            val response = eatWiseApi.getAllMeals(page = page)

            if (response.meals.isNotEmpty()) {
                eatWiseDatabase.withTransaction {
                    if (loadType == LoadType.REFRESH) {
                        mealDao.deleteAllMeals()
                        mealRemoteKeysDao.deleteAllRemoteKeys()
                    }
                    val prevPage = response.prevPage
                    val nextPage = response.nextPage
                    val keys = response.meals.map { meal ->
                        MealRemoteKeys(
                            id = meal.id,
                            prevPage = prevPage,
                            nextPage = nextPage,
                            lastUpdated = response.lastUpdated
                        )
                    }
                    mealRemoteKeysDao.addAllRemoteKeys(mealRemoteKeys = keys)
                    mealDao.addMeals(meals = response.meals)
                }
            }


            MediatorResult.Success(endOfPaginationReached = response.nextPage == null)


        } catch (e: Exception) {
            return MediatorResult.Error(e)
        }
    }


    private suspend fun getRemoteKeyClosestCurrentPosition(
        state: PagingState<Int, MealInfo>
    ): MealRemoteKeys? {
        Log.d("PageNumber", "refresh")
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { id ->
                mealRemoteKeysDao.getRemoteKeys(id = id)
            }
        }
    }


    private suspend fun getRemoteKeyForFirstItem(
        state: PagingState<Int, MealInfo>
    ): MealRemoteKeys? {
        Log.d("PageNumber", "prepend")
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()
            ?.let { hero ->
                mealRemoteKeysDao.getRemoteKeys(id = hero.id)
            }
    }

    private suspend fun getRemoteKeyForLastItem(
        state: PagingState<Int, MealInfo>
    ): MealRemoteKeys? {
        Log.d("PageNumber", "append")
        return state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()
            ?.let { hero ->
                mealRemoteKeysDao.getRemoteKeys(id = hero.id)
            }
    }


}



