package com.demircandemir.relaysample.di

import android.content.Context
import com.demircandemir.relaysample.data.repository.DataStoreOperationsImpl
import com.demircandemir.relaysample.data.repository.Repository
import com.demircandemir.relaysample.domain.repositories.DataStoreOperations
import com.demircandemir.relaysample.domain.use_cases.UseCases
import com.demircandemir.relaysample.domain.use_cases.get_all_meals.GetAllMealsUseCase
import com.demircandemir.relaysample.domain.use_cases.get_selected_meal.GetSelectedMealUseCase
import com.demircandemir.relaysample.domain.use_cases.get_user_info.GetUserInfoUseCase
import com.demircandemir.relaysample.domain.use_cases.post_user_info.PostUserInfoUseCase
import com.demircandemir.relaysample.domain.use_cases.read_survey.ReadSurveyStateUseCases
import com.demircandemir.relaysample.domain.use_cases.save_survey.SaveSurveyUseCase
import com.demircandemir.relaysample.domain.use_cases.search_meals.SearchMealsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideDataStoreOperations(
        @ApplicationContext context: Context
    ): DataStoreOperations {
        return DataStoreOperationsImpl(context = context)
    }


    @Provides
    @Singleton
    fun provideUseCases(
        repository: Repository
    ): UseCases {
        return UseCases(
            getAllMealsUseCase = GetAllMealsUseCase(repository = repository),
            getSelectedMealUseCase = GetSelectedMealUseCase(repository = repository),
            getUserInfoUseCase = GetUserInfoUseCase(repository = repository),
            postUserInfoUseCase = PostUserInfoUseCase(repository = repository),
            searchMealsUseCase = SearchMealsUseCase(repository = repository),
            readSurveyUseCase = ReadSurveyStateUseCases(repository = repository),
            saveSurveyUseCase = SaveSurveyUseCase(repository = repository)
        )
    }
}