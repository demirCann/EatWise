package com.demircandemir.relaysample.domain.use_cases.get_selected_meal

import com.demircandemir.relaysample.data.repository.Repository
import com.demircandemir.relaysample.domain.model.MealInfo

class GetSelectedMealUseCase (
    private val repository: Repository
){
    suspend operator fun invoke(id: Int): MealInfo {
        return repository.getSelectedMeals(id = id)
    }
}