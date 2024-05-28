package com.demircandemir.relaysample.data.repository

import com.demircandemir.relaysample.domain.model.open_ai.Message
import com.demircandemir.relaysample.domain.model.open_ai.OpenAIResponse
import com.demircandemir.relaysample.domain.repositories.OpenAIDataSource
import com.demircandemir.relaysample.util.ApiResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AIRepository @Inject constructor(
    private val openAIDataSource: OpenAIDataSource
) {
    fun sendMessageOpenAi(list: List<Message>): Flow<ApiResult<OpenAIResponse>> = openAIDataSource.sendMessageOpenAi(list)
}

