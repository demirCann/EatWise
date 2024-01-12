package com.demircandemir.relaysample.domain.use_cases.save_survey

import com.demircandemir.relaysample.data.repository.Repository

class SaveSurveyUseCase(
    private val repository: Repository
) {
    suspend operator fun invoke(completed: Boolean) {
        repository.saveSurveyState(completed)
    }
}