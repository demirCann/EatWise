package com.demircandemir.relaysample.data.repository.ai

import com.demircandemir.relaysample.domain.model.AIModel
import com.demircandemir.relaysample.domain.model.chat.ChatMessage
import com.demircandemir.relaysample.util.ApiResult
import kotlinx.coroutines.flow.Flow

interface AIRepository {
    fun sendMessage(message: ChatMessage, model: AIModel): Flow<ApiResult<ChatMessage>>
} 