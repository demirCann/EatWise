package com.demircandemir.relaysample.util

import com.demircandemir.relaysample.util.Constants.OPENAI_API_KEY
import okhttp3.Interceptor
import okhttp3.Response

class ApiInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val apiKey = OPENAI_API_KEY
        val newRequest = originalRequest.newBuilder()
            .header("Authorization", "Bearer $apiKey")
            .build()

        return chain.proceed(newRequest)
    }
}