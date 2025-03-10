package com.demircandemir.relaysample.domain.use_cases.get_diet_plan

import com.demircandemir.relaysample.data.repository.meal.MealRepository
import com.demircandemir.relaysample.domain.model.MealsForDietPlan
import com.demircandemir.relaysample.util.ApiResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetDietPlanUseCase @Inject constructor(
    val mealRepository: MealRepository
) {
    suspend operator fun invoke(userId: Int, repast: String): Flow<ApiResult<MealsForDietPlan>> {
        return mealRepository.getDietPlan(userId, repast)
    }
}