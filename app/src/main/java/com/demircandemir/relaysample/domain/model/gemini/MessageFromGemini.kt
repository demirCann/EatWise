package com.demircandemir.relaysample.domain.model.gemini

import android.graphics.Bitmap

data class MessageFromGemini(
    val text: String,
    val isUser: Boolean,
    val timestamp: String,
    val image: Bitmap? = null
)
