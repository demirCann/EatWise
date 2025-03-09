package com.demircandemir.relaysample.domain.use_cases.search_meals

import androidx.paging.PagingData
import com.demircandemir.relaysample.data.repository.meal.MealRepository
import com.demircandemir.relaysample.domain.model.MealInfo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchMealsUseCase @Inject constructor(
    private val mealRepository: MealRepository
) {
    operator fun invoke(query: String): Flow<PagingData<MealInfo>> {
        return mealRepository.searchMeals(query = query)
    }
}