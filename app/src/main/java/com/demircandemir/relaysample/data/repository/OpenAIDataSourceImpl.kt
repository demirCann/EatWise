package com.demircandemir.relaysample.data.repository

import com.demircandemir.relaysample.data.remote.OpenAIApi
import com.demircandemir.relaysample.domain.model.open_ai.Message
import com.demircandemir.relaysample.domain.model.open_ai.OpenAIRequestBody
import com.demircandemir.relaysample.domain.model.open_ai.OpenAIResponse
import com.demircandemir.relaysample.domain.repositories.OpenAIDataSource
import com.demircandemir.relaysample.util.ApiResult
import com.demircandemir.relaysample.util.apiFlow2
import kotlinx.coroutines.flow.Flow

class OpenAIDataSourceImpl(
    private val openAIApi: OpenAIApi
): OpenAIDataSource {
    override fun sendMessageOpenAi(list: List<Message>): Flow<ApiResult<OpenAIResponse>> {
        return apiFlow2 { openAIApi.generateResponse(OpenAIRequestBody(messages = list)) }
    }
}