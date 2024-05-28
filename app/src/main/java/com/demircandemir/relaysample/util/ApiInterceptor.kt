package com.demircandemir.relaysample.util

import okhttp3.Interceptor
import okhttp3.Response

class ApiInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val apiKey = "sk-proj-5WEuAWtDaeajemwfu71uT3BlbkFJJK1xri6HGDnUDdUq5w7I"
        val newRequest = originalRequest.newBuilder()
            .header("Authorization", "Bearer $apiKey")
            .build()

        return chain.proceed(newRequest)
    }
}