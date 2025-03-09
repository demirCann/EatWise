package com.demircandemir.relaysample.data.datasource.firebase

import android.content.Intent
import android.content.IntentSender
import com.demircandemir.relaysample.domain.model.FirebaseUserData
import com.demircandemir.relaysample.domain.model.UserInfo
import com.demircandemir.relaysample.util.ApiResult
import kotlinx.coroutines.flow.Flow

interface FirebaseDataSource {
    suspend fun signIn(): ApiResult<IntentSender>
    suspend fun signInWithIntent(intent: Intent): ApiResult<FirebaseUserData>
    suspend fun signOut(): ApiResult<Void>
    fun getSignedInUser(): ApiResult<FirebaseUserData?>
    suspend fun getUserInfoFromRemote(): Flow<ApiResult<UserInfo>>
    suspend fun postUserInfoToRemote(
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
    )
}