package com.demircandemir.relaysample.data.datasource.ai

import com.demircandemir.relaysample.data.remote.OpenAIApi
import com.demircandemir.relaysample.domain.model.chat.ChatMessage
import com.demircandemir.relaysample.domain.model.chat.MessageContent
import com.demircandemir.relaysample.domain.model.chat.MessageSender
import com.demircandemir.relaysample.domain.model.open_ai.OpenAIMessage
import com.demircandemir.relaysample.domain.model.open_ai.OpenAIRequestBody
import com.demircandemir.relaysample.domain.model.open_ai.OpenAIResponse
import com.demircandemir.relaysample.util.ApiResult
import com.demircandemir.relaysample.util.Constants
import com.demircandemir.relaysample.util.Constants.AI_DEFAULT_IMAGE_PROMPT
import com.demircandemir.relaysample.util.Constants.ERROR_EMPTY_RESPONSE_GEMINI
import com.demircandemir.relaysample.util.Constants.GEMINI_1_5_FLASH
import com.demircandemir.relaysample.util.Constants.GEMINI_PRO
import com.demircandemir.relaysample.util.apiFlow2
import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.type.BlockThreshold
import com.google.ai.client.generativeai.type.HarmCategory
import com.google.ai.client.generativeai.type.SafetySetting
import com.google.ai.client.generativeai.type.content
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AIDataSourceImpl @Inject constructor(
    private val openAIApi: OpenAIApi
) : AIDataSource {

    private val harassmentSafety = SafetySetting(HarmCategory.HARASSMENT, BlockThreshold.ONLY_HIGH)
    private val sexualContentSafety = SafetySetting(HarmCategory.SEXUALLY_EXPLICIT, BlockThreshold.ONLY_HIGH)
    private val hateSpeechSafety = SafetySetting(HarmCategory.HATE_SPEECH, BlockThreshold.MEDIUM_AND_ABOVE)

    private val generativeModelVision by lazy { createGenerativeModel(GEMINI_1_5_FLASH) }
    private val generativeModelText by lazy { createGenerativeModel(GEMINI_PRO) }

    private val safetySettings by lazy {
        listOf(
            harassmentSafety,
            sexualContentSafety,
            hateSpeechSafety
        )
    }

    private fun createGenerativeModel(modelName: String) = GenerativeModel(
        modelName = modelName,
        apiKey = Constants.GEMINI_API_KEY,
        safetySettings = safetySettings
    )

    override fun sendMessageOpenAi(message: OpenAIMessage): Flow<ApiResult<OpenAIResponse>> = apiFlow2 {
        openAIApi.generateResponse(OpenAIRequestBody(openAIMessages = listOf(message)))
    }

    override fun sendMessageGemini(message: ChatMessage): Flow<ApiResult<ChatMessage>> = flow {
        try {
            emit(ApiResult.Loading)
            
            when (val content = message.content) {
                is MessageContent.Text -> {
                    val response = generativeModelText.generateContent(content.text)
                    
                    if (response.text != null) {
                        emit(ApiResult.Success(
                            ChatMessage(
                                content = MessageContent.Text(response.text!!),
                                sender = MessageSender.AI.Gemini
                            )
                        ))
                    } else {
                        emit(ApiResult.Error(ERROR_EMPTY_RESPONSE_GEMINI))
                    }
                }
                else -> throw IllegalArgumentException("Invalid message type for text-only model")
            }
        } catch (e: Exception) {
            emit(ApiResult.Error(e.message ?: "An error occurred"))
        }
    }

    override fun sendImageMessageGemini(message: ChatMessage): Flow<ApiResult<ChatMessage>> = flow {
        try {
            emit(ApiResult.Loading)
            
            when (val content = message.content) {
                is MessageContent.Image -> {
                    val inputContent = content {
                        image(content.image)
                        text(content.caption ?: AI_DEFAULT_IMAGE_PROMPT)
                    }
                    
                    val response = generativeModelVision.generateContent(inputContent)
                    
                    if (response.text != null) {
                        emit(ApiResult.Success(
                            ChatMessage(
                                content = MessageContent.Text(response.text!!),
                                sender = MessageSender.AI.Gemini
                            )
                        ))
                    } else {
                        emit(ApiResult.Error(ERROR_EMPTY_RESPONSE_GEMINI))
                    }
                }
                is MessageContent.TextWithImage -> {
                    val inputContent = content {
                        image(content.image)
                        text(content.text)
                    }
                    
                    val response = generativeModelVision.generateContent(inputContent)
                    
                    if (response.text != null) {
                        emit(ApiResult.Success(
                            ChatMessage(
                                content = MessageContent.Text(response.text!!),
                                sender = MessageSender.AI.Gemini
                            )
                        ))
                    } else {
                        emit(ApiResult.Error(ERROR_EMPTY_RESPONSE_GEMINI))
                    }
                }
                else -> throw IllegalArgumentException("Invalid message type for vision model")
            }
        } catch (e: Exception) {
            emit(ApiResult.Error(e.message ?: "An error occurred"))
        }
    }
} 