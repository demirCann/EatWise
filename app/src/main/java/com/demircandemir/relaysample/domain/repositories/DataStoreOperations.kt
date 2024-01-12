package com.demircandemir.relaysample.domain.repositories

import kotlinx.coroutines.flow.Flow

interface DataStoreOperations {

    suspend fun saveSurveyState(completed: Boolean)

    fun readSurveyState(): Flow<Boolean>
}