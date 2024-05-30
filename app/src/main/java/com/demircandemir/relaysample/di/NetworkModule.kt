package com.demircandemir.relaysample.di

import com.demircandemir.relaysample.data.local.EatWiseDatabase
import com.demircandemir.relaysample.data.remote.EatWiseApi
import com.demircandemir.relaysample.data.repository.RemoteDataSourceImpl
import com.demircandemir.relaysample.domain.repositories.RemoteDataSource
import com.demircandemir.relaysample.util.Constants.BASE_URL
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import javax.inject.Qualifier
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {





    @Singleton
    @Provides
    @MainOkHttpClient
    fun provideHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .readTimeout(15, TimeUnit.SECONDS)
            .connectTimeout(15, TimeUnit.SECONDS)
            .build()
    }


    @OptIn(ExperimentalSerializationApi::class)
    @Singleton
    @Provides
    @MainRetrofit
    fun provideRetrofit(@MainOkHttpClient okHttpClient: OkHttpClient): Retrofit {
        val contentType = "application/json".toMediaType()
        val json = Json {
            ignoreUnknownKeys = true
            isLenient = true
        }
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(json.asConverterFactory(contentType))
            .build()
    }


    @Provides
    @Singleton
    fun provideUserApi(@MainRetrofit retrofit: Retrofit): EatWiseApi {
        return retrofit.create(EatWiseApi::class.java)
    }

    @Provides
    @Singleton
    fun provideRemoteDataSource(
        eatWiseApi: EatWiseApi,
        eatWiseDatabase: EatWiseDatabase
    ): RemoteDataSource {
        return RemoteDataSourceImpl(
            eatWiseApi = eatWiseApi,
            eatWiseDatabase = eatWiseDatabase
        )
    }

}


@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class MainOkHttpClient

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class MainRetrofit