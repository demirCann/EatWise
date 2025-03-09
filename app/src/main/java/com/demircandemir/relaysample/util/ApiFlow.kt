package com.demircandemir.relaysample.util

import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import com.demircandemir.relaysample.util.Constants.ERROR_OCCURRED
import com.demircandemir.relaysample.util.Constants.RESPONSE_NULL
import com.demircandemir.relaysample.util.Constants.API_REQUEST_LIMIT
import com.demircandemir.relaysample.util.Constants.UNKNOWN_HTTP_ERROR

val gson = Gson()

fun <T> apiFlow(
    call: suspend () -> Response<T>?
): Flow<ApiResult<T>> = flow {
    emit(ApiResult.Loading)

    try {
        val c = call()
        c?.let {
            if (c.isSuccessful) {
                val body = c.body()
                if (body != null) {
                    emit(ApiResult.Success(body))
                } else {
                    emit(ApiResult.Error(ERROR_OCCURRED))
                }
            } else {
                val errorBody = c.errorBody()?.string()
                val errorResponse = gson.fromJson(errorBody, ApiErrorResponse::class.java)
                emit(ApiResult.Error(errorResponse.error))
            }
        } ?: emit(ApiResult.Error(RESPONSE_NULL))
    } catch (e: HttpException) {
        val errorBody = e.response()?.errorBody()?.string()
        val errorMessage = when (e.code()) {
            429 -> API_REQUEST_LIMIT
            else -> errorBody ?: UNKNOWN_HTTP_ERROR
        }
        emit(ApiResult.Error(errorMessage))
    } catch (e: IOException) {
        emit(ApiResult.Error(e.message ?: ERROR_OCCURRED))
    } catch (e: Exception) {
        emit(ApiResult.Error(e.message ?: ERROR_OCCURRED))
    }
}.flowOn(Dispatchers.IO)

sealed class ApiResult<out T> {
    data class Success<out R>(val data: R?) : ApiResult<R>()
    data class Error(val message: String?) : ApiResult<Nothing>()
    data object Loading : ApiResult<Nothing>()
}

data class ApiErrorResponse(
    val error: String
)