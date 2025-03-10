package com.demircandemir.relaysample.domain.use_cases.get_daily_meals

import com.demircandemir.relaysample.domain.model.DailyMeals
import com.demircandemir.relaysample.data.repository.meal.MealRepository
import com.demircandemir.relaysample.domain.model.MealType
import com.demircandemir.relaysample.util.ApiResult
import com.demircandemir.relaysample.util.Constants.ERROR_OCCURRED
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetDailyMealsUseCase @Inject constructor(
    private val mealRepository: MealRepository
) {
    operator fun invoke(userId: Int): Flow<ApiResult<DailyMeals>> = flow {
        emit(ApiResult.Loading)
        try {
            combine(
                mealRepository.getDietPlan(userId, MealType.BREAKFAST.value),
                mealRepository.getDietPlan(userId, MealType.LUNCH.value),
                mealRepository.getDietPlan(userId, MealType.DINNER.value),
                mealRepository.getDietPlan(userId, MealType.SNACK.value)
            ) { breakfast, lunch, dinner, snacks ->
                
                when {
                    breakfast is ApiResult.Error -> ApiResult.Error(breakfast.message ?: ERROR_OCCURRED)
                    lunch is ApiResult.Error -> ApiResult.Error(lunch.message ?: ERROR_OCCURRED)
                    dinner is ApiResult.Error -> ApiResult.Error(dinner.message ?: ERROR_OCCURRED)
                    snacks is ApiResult.Error -> ApiResult.Error(snacks.message ?: ERROR_OCCURRED)
                    
                    breakfast is ApiResult.Success && 
                    lunch is ApiResult.Success && 
                    dinner is ApiResult.Success && 
                    snacks is ApiResult.Success -> {
                        val dailyMeals = DailyMeals(
                            breakfast = breakfast.data!!,
                            lunch = lunch.data!!,
                            dinner = dinner.data!!,
                            snacks = snacks.data!!
                        )
                        ApiResult.Success(dailyMeals)
                    }
                    
                    else -> ApiResult.Loading
                }
            }.collect { result ->
                emit(result)
            }
        } catch (e: Exception) {
            emit(ApiResult.Error(e.message ?: ERROR_OCCURRED))
        }
    }
} 