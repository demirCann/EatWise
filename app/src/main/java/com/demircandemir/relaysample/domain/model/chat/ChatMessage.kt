package com.demircandemir.relaysample.domain.model.chat

import android.graphics.Bitmap
import java.util.UUID

data class ChatMessage(
    val id: String = UUID.randomUUID().toString(),
    val content: MessageContent,
    val sender: MessageSender,
    val timestamp: Long = System.currentTimeMillis(),
    val status: MessageStatus = MessageStatus.SENT,
    val replyTo: String? = null
)

sealed class MessageContent {
    data class Text(val text: String) : MessageContent()
    data class Image(val image: Bitmap, val caption: String? = null) : MessageContent()
    data class TextWithImage(val text: String, val image: Bitmap) : MessageContent()
}

sealed class MessageSender {
    data class User(val id: String, val name: String) : MessageSender()
    sealed class AI : MessageSender() {
        data object Gemini : AI()
        data object GPT : AI()
    }
}

enum class MessageStatus {
    SENDING, SENT, ERROR
}