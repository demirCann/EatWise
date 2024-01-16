package com.demircandemir.relaysample.di

import android.content.Context
import androidx.room.Room
import com.demircandemir.relaysample.data.local.EatWiseDatabase
import com.demircandemir.relaysample.data.repository.LocalDataSourceImpl
import com.demircandemir.relaysample.domain.repositories.LocalDataSource
import com.demircandemir.relaysample.util.Constants.EAT_WISE_DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): EatWiseDatabase {
        return Room.databaseBuilder(
            context,
            EatWiseDatabase::class.java,
            EAT_WISE_DATABASE_NAME
        ).build()
    }


    @Provides
    @Singleton
    fun provideLocalDataSource(
        eatWiseDatabase: EatWiseDatabase
    ): LocalDataSource {
        return LocalDataSourceImpl(eatWiseDatabase = eatWiseDatabase)
    }
}