package com.demircandemir.relaysample.domain.repositories

import com.demircandemir.relaysample.domain.model.open_ai.Message
import com.demircandemir.relaysample.domain.model.open_ai.OpenAIResponse
import com.demircandemir.relaysample.util.ApiResult
import kotlinx.coroutines.flow.Flow

interface OpenAIDataSource {

    fun sendMessageOpenAi(
        list: List<Message>,
    ): Flow<ApiResult<OpenAIResponse>>


}