package com.demircandemir.relaysample.di

import com.demircandemir.relaysample.data.datasource.ai.AIDataSourceImpl
import com.demircandemir.relaysample.data.remote.OpenAIApi
import com.demircandemir.relaysample.data.datasource.ai.AIDataSource
import com.demircandemir.relaysample.util.ApiInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Qualifier
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AIModule {

    private val logging = HttpLoggingInterceptor().apply {
        setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    @Provides
    @Singleton
    @OpenAIOkHttpClient
    fun provideOkHttpClient2(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(ApiInterceptor())
            .addInterceptor(logging)
            .build()
    }

    @Provides
    @Singleton
    @OpenAIRetrofit
    fun provideOpenAIRetrofit(@OpenAIOkHttpClient okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.openai.com/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideOpenAIApi(@OpenAIRetrofit retrofit: Retrofit): OpenAIApi {
        return retrofit.create(OpenAIApi::class.java)
    }

    @Provides
    @Singleton
    fun provideAIDataSource(openAIApi: OpenAIApi): AIDataSource {
        return AIDataSourceImpl(openAIApi)
    }
}

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class OpenAIOkHttpClient

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class OpenAIRetrofit