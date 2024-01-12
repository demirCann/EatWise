package com.demircandemir.relaysample.domain.use_cases.search_meals

import androidx.paging.PagingData
import com.demircandemir.relaysample.data.repository.Repository
import com.demircandemir.relaysample.domain.model.MealInfo
import kotlinx.coroutines.flow.Flow

class SearchMealsUseCase(
    private val repository: Repository
) {
    operator fun invoke(name: String): Flow<PagingData<MealInfo>> {
        return repository.searchMeals(name = name)
    }
}