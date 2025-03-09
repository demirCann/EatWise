package com.demircandemir.relaysample.data.datasource.ai

import com.demircandemir.relaysample.domain.model.chat.ChatMessage
import com.demircandemir.relaysample.domain.model.open_ai.OpenAIMessage
import com.demircandemir.relaysample.domain.model.open_ai.OpenAIResponse
import com.demircandemir.relaysample.util.ApiResult
import kotlinx.coroutines.flow.Flow

interface AIDataSource {
    fun sendMessageOpenAi(message: OpenAIMessage): Flow<ApiResult<OpenAIResponse>>
    fun sendMessageGemini(message: ChatMessage): Flow<ApiResult<ChatMessage>>
    fun sendImageMessageGemini(message: ChatMessage): Flow<ApiResult<ChatMessage>>
} 