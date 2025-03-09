package com.demircandemir.relaysample.di

import android.content.Context
import com.demircandemir.relaysample.data.datasource.firebase.FirebaseDataSource
import com.demircandemir.relaysample.data.datasource.firebase.FirebaseDataSourceImpl
import com.demircandemir.relaysample.data.remote.EatWiseApi
import com.google.android.gms.auth.api.identity.Identity
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FirebaseModule {

    @Provides
    @Singleton
    fun provideOneTapClient(
        @ApplicationContext context: Context
    ): SignInClient {
        return Identity.getSignInClient(context)
    }

    @Provides
    @Singleton
    fun provideFirebaseAuth(): FirebaseAuth {
        return FirebaseAuth.getInstance()
    }

    @Provides
    @Singleton
    fun provideFirebaseDataSource(
        firebaseAuth: FirebaseAuth,
        oneTapClientTap: SignInClient,
        eatWiseApi: EatWiseApi
    ): FirebaseDataSource {
        return FirebaseDataSourceImpl(firebaseAuth, oneTapClientTap, eatWiseApi)
    }
} 