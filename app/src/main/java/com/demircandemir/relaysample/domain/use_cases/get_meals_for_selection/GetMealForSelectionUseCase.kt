package com.demircandemir.relaysample.domain.use_cases.get_meals_for_selection

import androidx.paging.PagingData
import com.demircandemir.relaysample.data.repository.Repository
import com.demircandemir.relaysample.domain.model.MealInfo
import kotlinx.coroutines.flow.Flow

class GetMealForSelectionUseCase(
    private val repository: Repository

) {

    operator fun invoke(repast: String): Flow<PagingData<MealInfo>> {
        return repository.getMealForSelection(repast = repast)
    }
}