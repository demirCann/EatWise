package com.demircandemir.relaysample.data.repository.ai

import com.demircandemir.relaysample.data.datasource.ai.AIDataSource
import com.demircandemir.relaysample.domain.model.AIModel
import com.demircandemir.relaysample.domain.model.chat.ChatMessage
import com.demircandemir.relaysample.domain.model.chat.MessageContent
import com.demircandemir.relaysample.domain.model.chat.MessageSender
import com.demircandemir.relaysample.domain.model.open_ai.OpenAIMessage
import com.demircandemir.relaysample.domain.model.open_ai.OpenAIResponse
import com.demircandemir.relaysample.util.ApiResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class AIRepositoryImpl(private val aiDataSource: AIDataSource) : AIRepository {
    override fun sendMessage(message: ChatMessage, model: AIModel): Flow<ApiResult<ChatMessage>> {
        return when (model) {
            AIModel.GPT -> aiDataSource.sendMessageOpenAi(message.toOpenAIMessage()).map { result ->
                when (result) {
                    is ApiResult.Success -> ApiResult.Success(result.data?.toChatMessage())
                    is ApiResult.Error -> ApiResult.Error(result.message)
                    is ApiResult.Loading -> ApiResult.Loading
                }
            }
            AIModel.GEMINI -> aiDataSource.sendMessageGemini(message)
        }
    }
}

private fun ChatMessage.toOpenAIMessage(): OpenAIMessage {
    return OpenAIMessage(
        content = when (val messageContent = this.content) {
            is MessageContent.Text -> messageContent.text
            is MessageContent.Image -> messageContent.caption ?: ""
            is MessageContent.TextWithImage -> messageContent.text
        },
        role = when (this.sender) {
            is MessageSender.User -> "user"
            is MessageSender.AI -> "assistant"
        }
    )
}

private fun OpenAIResponse.toChatMessage() = ChatMessage(
    content = MessageContent.Text(this.choices.first().openAIMessage.content),
    sender = MessageSender.AI.GPT
)