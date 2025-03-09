package com.demircandemir.relaysample.di

import android.content.Context
import com.demircandemir.relaysample.data.datasource.ai.AIDataSource
import com.demircandemir.relaysample.data.datasource.data_store.DataStoreSourceImpl
import com.demircandemir.relaysample.data.repository.ai.AIRepository
import com.demircandemir.relaysample.data.repository.meal.MealRepository
import com.demircandemir.relaysample.data.datasource.data_store.DataStoreSource
import com.demircandemir.relaysample.data.datasource.firebase.FirebaseDataSource
import com.demircandemir.relaysample.data.datasource.remote.RemoteDataSource
import com.demircandemir.relaysample.data.repository.ai.AIRepositoryImpl
import com.demircandemir.relaysample.data.repository.firebase.FirebaseRepository
import com.demircandemir.relaysample.data.repository.firebase.FirebaseRepositoryImpl
import com.demircandemir.relaysample.data.repository.meal.MealRepositoryImpl
import com.demircandemir.relaysample.domain.use_cases.UseCases
import com.demircandemir.relaysample.domain.use_cases.get_all_meals.GetAllMealsUseCase
import com.demircandemir.relaysample.domain.use_cases.get_daily_meals.GetDailyMealsUseCase
import com.demircandemir.relaysample.domain.use_cases.get_diet_plan.GetDietPlanUseCase
import com.demircandemir.relaysample.domain.use_cases.get_meals_for_selection.GetMealForSelectionUseCase
import com.demircandemir.relaysample.domain.use_cases.get_selected_meal.GetSelectedMealUseCase
import com.demircandemir.relaysample.domain.use_cases.get_user_info.GetUserInfoFromRemoteUseCase
import com.demircandemir.relaysample.domain.use_cases.post_diet_plan.PostDietPlanUseCase
import com.demircandemir.relaysample.domain.use_cases.post_user_info.PostUserInfoToRemoteUseCase
import com.demircandemir.relaysample.domain.use_cases.read_survey.ReadSurveyStateUseCases
import com.demircandemir.relaysample.domain.use_cases.save_survey.SaveSurveyUseCase
import com.demircandemir.relaysample.domain.use_cases.search_meals.SearchMealsUseCase
import com.demircandemir.relaysample.domain.use_cases.upate_diet_plan.UpdateDietPlanUseCase
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
    ): DataStoreSource {
        return DataStoreSourceImpl(context = context)
    }

    @Provides
    @Singleton
    fun provideMealRepository(
        remoteDataSource: RemoteDataSource,
        dataStoreSource: DataStoreSource
    ): MealRepository {
        return MealRepositoryImpl(
            remoteDataSource,
            dataStoreSource
        )
    }

    @Provides
    @Singleton
    fun provideAIRepository(
        aiDataSource: AIDataSource
    ): AIRepository {
        return AIRepositoryImpl(aiDataSource)
    }

    @Provides
    @Singleton
    fun provideFirebaseRepository(
        firebaseDataSource: FirebaseDataSource
    ): FirebaseRepository {
        return FirebaseRepositoryImpl(firebaseDataSource)
    }

    @Provides
    @Singleton
    fun provideUseCases(
        mealRepository: MealRepository,
        firebaseRepository: FirebaseRepository
    ): UseCases {
        return UseCases(
            getAllMealsUseCase = GetAllMealsUseCase(mealRepository = mealRepository),
            getMealForSelectionUseCase = GetMealForSelectionUseCase(mealRepository = mealRepository),
            getSelectedMealUseCase = GetSelectedMealUseCase(mealRepository = mealRepository),
            getUserInfoFromRemoteUseCase = GetUserInfoFromRemoteUseCase(firebaseRepository = firebaseRepository),
            postUserInfoToRemoteUseCase = PostUserInfoToRemoteUseCase(firebaseRepository = firebaseRepository),
            searchMealsUseCase = SearchMealsUseCase(mealRepository = mealRepository),
            readSurveyUseCase = ReadSurveyStateUseCases(mealRepository = mealRepository),
            saveSurveyUseCase = SaveSurveyUseCase(mealRepository = mealRepository),
            postDietPlanUseCase = PostDietPlanUseCase(mealRepository = mealRepository),
            getDietPlanUseCase = GetDietPlanUseCase(mealRepository = mealRepository),
            getDailyMealsUseCase = GetDailyMealsUseCase(mealRepository = mealRepository),
            updateDietPlanUseCase = UpdateDietPlanUseCase(mealRepository = mealRepository)
        )
    }
}