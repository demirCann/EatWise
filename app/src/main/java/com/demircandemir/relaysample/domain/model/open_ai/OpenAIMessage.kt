package com.demircandemir.relaysample.domain.model.open_ai

data class OpenAIMessage(
    val content: String,
    val role: String,
) {
    val isUser: Boolean
        get() = role == "user"
}