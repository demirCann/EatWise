package com.demircandemir.relaysample.domain.use_cases.get_user_info

import com.demircandemir.relaysample.data.repository.firebase.FirebaseRepository
import com.demircandemir.relaysample.domain.model.UserInfo
import com.demircandemir.relaysample.util.ApiResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUserInfoFromRemoteUseCase @Inject constructor(
    private val firebaseRepository: FirebaseRepository
) {
    suspend operator fun invoke(): Flow<ApiResult<UserInfo>> {
        return firebaseRepository.getUserInfoFromRemote()
    }
}