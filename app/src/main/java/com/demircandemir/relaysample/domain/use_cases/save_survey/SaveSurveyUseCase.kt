package com.demircandemir.relaysample.domain.use_cases.save_survey

import com.demircandemir.relaysample.data.repository.meal.MealRepository
import javax.inject.Inject

class SaveSurveyUseCase @Inject constructor(
    private val mealRepository: MealRepository
) {
    suspend operator fun invoke(completed: Boolean) {
        mealRepository.saveSurveyState(completed)
    }
}