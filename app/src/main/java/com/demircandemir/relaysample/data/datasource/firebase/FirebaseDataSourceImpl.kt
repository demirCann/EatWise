package com.demircandemir.relaysample.data.datasource.firebase

import android.content.Intent
import android.content.IntentSender
import com.demircandemir.relaysample.data.remote.EatWiseApi
import com.demircandemir.relaysample.domain.model.FirebaseUserData
import com.demircandemir.relaysample.domain.model.UserInfo
import com.demircandemir.relaysample.util.ApiResult
import com.demircandemir.relaysample.util.Constants.FIREBASE_SERVER_CLIENT_ID
import com.demircandemir.relaysample.util.apiFlow
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.BeginSignInRequest.GoogleIdTokenRequestOptions
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FirebaseDataSourceImpl @Inject constructor(
    private val auth: FirebaseAuth,
    private val oneTapClient: SignInClient,
    private val eatWiseApi: EatWiseApi
) : FirebaseDataSource {

    override suspend fun signIn(): ApiResult<IntentSender> {
        return try {
            val signInRequest = BeginSignInRequest.Builder().setGoogleIdTokenRequestOptions(
                    GoogleIdTokenRequestOptions.builder().setSupported(true)
                        .setFilterByAuthorizedAccounts(false)
                        .setServerClientId(FIREBASE_SERVER_CLIENT_ID).build()
                ).setAutoSelectEnabled(true).build()
            ApiResult.Success(
                oneTapClient.beginSignIn(
                    signInRequest
                ).await().pendingIntent.intentSender
            )
        } catch (e: Exception) {
            ApiResult.Error(e.message)
        }
    }

    override suspend fun signInWithIntent(intent: Intent): ApiResult<FirebaseUserData> {
        val credential = oneTapClient.getSignInCredentialFromIntent(intent)
        val googleIdToken = credential.googleIdToken
        val googleCredentials = GoogleAuthProvider.getCredential(googleIdToken, null)
        return try {
            val user = auth.signInWithCredential(googleCredentials).await().user
            ApiResult.Success(user?.run {
                FirebaseUserData(
                    userId = uid,
                    userName = user.displayName,
                    profilePictureUrl = user.photoUrl?.toString()
                )
            })
        } catch (e: Exception) {
            ApiResult.Error(e.message)
        }
    }

    override suspend fun signOut(): ApiResult<Void> {
        return try {
            oneTapClient.signOut().await()
            auth.signOut()
            ApiResult.Success(null)
        } catch (e: Exception) {
            ApiResult.Error(e.message)
        }
    }

    override fun getSignedInUser(): ApiResult<FirebaseUserData?> {
        return try {
            ApiResult.Success(auth.currentUser?.run {
                FirebaseUserData(
                    userId = uid,
                    userName = displayName,
                    profilePictureUrl = photoUrl?.toString()
                )
            })
        } catch (e: Exception) {
            ApiResult.Error(e.message)
        }
    }

    override suspend fun getUserInfoFromRemote(): Flow<ApiResult<UserInfo>> = apiFlow {
        eatWiseApi.getUserInfo()
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
        eatWiseApi.postUserInfo(id, name, goal, weight, height, age, gender, exerciseDayInAWeek, weightGoal, timeFrame, diet_type)
    }
}