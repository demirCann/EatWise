package com.demircandemir.relaysample.util

import coil.network.HttpException
import com.demircandemir.relaysample.domain.model.open_ai.OpenAIResponse
import kotlinx.coroutines.flow.flow

fun apiFlow2(
    call : suspend () -> OpenAIResponse
) = flow<ApiResult<OpenAIResponse>> {

    emit(ApiResult.Loading)
    try {
        val response = call()
        emit(ApiResult.Success(response))
    } catch (e: HttpException) {
        emit(ApiResult.Error(e.message ?: "An unexpected error occurred"))
    }

}