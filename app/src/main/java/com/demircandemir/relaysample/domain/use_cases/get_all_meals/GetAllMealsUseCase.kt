package com.demircandemir.relaysample.domain.use_cases.get_all_meals

import com.demircandemir.relaysample.data.repository.Repository

class GetAllMealsUseCase(
    private val repository: Repository
) {
    operator fun invoke() {
        repository.getAllMeals()
    }
}