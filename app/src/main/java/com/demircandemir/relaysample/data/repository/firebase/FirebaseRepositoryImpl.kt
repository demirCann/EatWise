package com.demircandemir.relaysample.data.repository.firebase

import android.content.Intent
import android.content.IntentSender
import com.demircandemir.relaysample.domain.model.FirebaseUserData
import com.demircandemir.relaysample.data.datasource.firebase.FirebaseDataSource
import com.demircandemir.relaysample.domain.model.UserInfo
import com.demircandemir.relaysample.util.ApiResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FirebaseRepositoryImpl @Inject constructor(
    private val firebaseDataSource: FirebaseDataSource
) : FirebaseRepository {

    override suspend fun signIn(): ApiResult<IntentSender> {
        return firebaseDataSource.signIn()
    }

    override suspend fun signInWithIntent(intent: Intent): ApiResult<FirebaseUserData> {
        return firebaseDataSource.signInWithIntent(intent)
    }

    override suspend fun signOut(): ApiResult<Void> {
        return firebaseDataSource.signOut()
    }

    override fun getSignedInUser(): ApiResult<FirebaseUserData?> {
        return firebaseDataSource.getSignedInUser()
    }

    override suspend fun getUserInfoFromRemote(): Flow<ApiResult<UserInfo>> {
        return firebaseDataSource.getUserInfoFromRemote()
    }

    override suspend fun postUserInfoToRemote(
        id: Int,
        name: String,
        image: String,
        goal: String,
        weight: String,
        height: String,
        age: String,
        gender: String,
        exerciseDayInAWeek: String,
        weightGoal: String,
        timeFrame: String,
        diet_type: String
    ) {
        return firebaseDataSource.postUserInfoToRemote(
            id,
            name,
            image,
            goal,
            weight,
            height,
            age,
            gender,
            exerciseDayInAWeek,
            weightGoal,
            timeFrame,
            diet_type
        )
    }
}