package com.demircandemir.relaysample.domain.use_cases.get_all_meals

import androidx.paging.PagingData
import com.demircandemir.relaysample.data.repository.Repository
import com.demircandemir.relaysample.domain.model.MealInfo
import kotlinx.coroutines.flow.Flow

class GetAllMealsUseCase(
    private val repository: Repository
) {
    operator fun invoke(): Flow<PagingData<MealInfo>> {
        return repository.getAllMeals()
    }
}