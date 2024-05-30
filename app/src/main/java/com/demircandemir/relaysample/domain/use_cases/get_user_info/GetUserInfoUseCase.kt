package com.demircandemir.relaysample.domain.use_cases.get_user_info

import com.demircandemir.relaysample.data.repository.Repository
import com.demircandemir.relaysample.domain.model.UserInfo

class GetUserInfoUseCase(
    private val repository: Repository
) {
    suspend operator fun invoke(): UserInfo {
        return repository.getUserInfo()
    }
}