package com.demircandemir.relaysample.data.datasource.data_store

import kotlinx.coroutines.flow.Flow

interface DataStoreSource {
    suspend fun saveSurveyState(completed: Boolean)
    fun readSurveyState(): Flow<Boolean>
}