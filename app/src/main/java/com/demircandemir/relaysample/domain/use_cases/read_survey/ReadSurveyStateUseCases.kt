package com.demircandemir.relaysample.domain.use_cases.read_survey

import com.demircandemir.relaysample.data.repository.meal.MealRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ReadSurveyStateUseCases @Inject constructor(
    private val mealRepository: MealRepository
) {
    operator fun invoke(): Flow<Boolean> {
        return mealRepository.readSurveyState()
    }
}