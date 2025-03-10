package com.demircandemir.relaysample.domain.use_cases.get_meals_for_selection

import androidx.paging.PagingData
import com.demircandemir.relaysample.data.repository.meal.MealRepository
import com.demircandemir.relaysample.domain.model.MealInfo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMealForSelectionUseCase @Inject constructor(
    private val mealRepository: MealRepository
) {
    operator fun invoke(repast: String): Flow<PagingData<MealInfo>> {
        return mealRepository.getMealsForSelection(repast = repast)
    }
}