package com.demircandemir.relaysample.data.remote

import com.demircandemir.relaysample.domain.model.open_ai.OpenAIRequestBody
import com.demircandemir.relaysample.domain.model.open_ai.OpenAIResponse
import retrofit2.http.Body
import retrofit2.http.POST

fun interface OpenAIApi {
    @POST("v1/chat/completions")
    suspend fun generateResponse(@Body requestBody: OpenAIRequestBody): OpenAIResponse
}