package com.demircandemir.relaysample.domain.use_cases.read_survey

import com.demircandemir.relaysample.data.repository.Repository
import kotlinx.coroutines.flow.Flow

class ReadSurveyStateUseCases(
    private val repository: Repository
) {
    operator fun invoke(): Flow<Boolean> {
        return repository.readSurveyState()
    }
}