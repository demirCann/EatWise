package com.demircandemir.relaysample.util

import coil.network.HttpException
import com.demircandemir.relaysample.domain.model.open_ai.OpenAIResponse
import com.demircandemir.relaysample.util.Constants.ERROR_GENERIC
import kotlinx.coroutines.flow.flow

fun apiFlow2(
    call : suspend () -> OpenAIResponse
) = flow {

    emit(ApiResult.Loading)
    try {
        val response = call()
        emit(ApiResult.Success(response))
    } catch (e: HttpException) {
        emit(ApiResult.Error(e.message ?: ERROR_GENERIC))
    }
}