package com.demircandemir.relaysample.domain.use_cases.upate_diet_plan

import com.demircandemir.relaysample.data.repository.meal.MealRepository
import com.demircandemir.relaysample.domain.model.MealsRequest
import javax.inject.Inject

class UpdateDietPlanUseCase @Inject constructor(
    private val mealRepository: MealRepository
) {
    suspend operator fun invoke(userId: Int, repast: String, meals: MealsRequest) {
        mealRepository.updateDietPlan(userId, repast, meals)
    }
}