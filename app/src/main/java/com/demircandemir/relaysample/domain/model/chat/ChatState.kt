package com.demircandemir.relaysample.domain.model.chat

import com.demircandemir.relaysample.domain.model.AIModel
import com.demircandemir.relaysample.domain.model.UserInfo

data class ChatState(
    val messages: Map<AIModel, List<ChatMessage>> = mapOf(
        AIModel.GEMINI to emptyList(),
        AIModel.GPT to emptyList()
    ),
    val currentModel: AIModel = AIModel.GEMINI,
    val userInfo: UserInfo = UserInfo(),
    val status: ChatStatus = ChatStatus.IDLE,
    val error: Error? = null
)

sealed class ChatStatus {
    data object IDLE : ChatStatus()
    data object TYPING : ChatStatus()
    data class Error(val message: String) : ChatStatus()
}

sealed class Error {
    data class Network(val message: String) : Error()
    data class AI(val message: String) : Error()
    data class Generic(val message: String) : Error()
}