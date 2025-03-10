package com.demircandemir.relaysample.domain.use_cases.get_selected_meal

import com.demircandemir.relaysample.data.repository.meal.MealRepository
import com.demircandemir.relaysample.domain.model.MealResponse
import com.demircandemir.relaysample.util.ApiResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSelectedMealUseCase @Inject constructor(
    private val mealRepository: MealRepository
) {
    suspend operator fun invoke(id: Int): Flow<ApiResult<MealResponse>> {
        return mealRepository.getSelectedMeal(mealId = id)
    }
}