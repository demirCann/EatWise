package com.demircandemir.relaysample.domain.use_cases.post_diet_plan

import com.demircandemir.relaysample.data.repository.meal.MealRepository
import javax.inject.Inject

class PostDietPlanUseCase @Inject constructor(
    private val mealRepository: MealRepository
){
    operator fun invoke(userId: Int, repast: String, meals: String) {
        mealRepository.postDietPlan(userId, repast, meals)
    }
}