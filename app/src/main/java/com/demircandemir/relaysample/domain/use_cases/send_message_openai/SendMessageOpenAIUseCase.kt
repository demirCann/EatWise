package com.demircandemir.relaysample.domain.use_cases.send_message_openai

import com.demircandemir.relaysample.data.repository.AIRepository
import com.demircandemir.relaysample.domain.model.open_ai.Message
import com.demircandemir.relaysample.domain.model.open_ai.OpenAIResponse
import com.demircandemir.relaysample.util.ApiResult
import kotlinx.coroutines.flow.Flow

class SendMessageOpenAIUseCase(
    private val AIRepository: AIRepository
) {

    operator fun invoke(list: List<Message>): Flow<ApiResult<OpenAIResponse>> = AIRepository.sendMessageOpenAi(list)
}