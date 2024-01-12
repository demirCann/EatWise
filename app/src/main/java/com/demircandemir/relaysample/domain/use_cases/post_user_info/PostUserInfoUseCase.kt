package com.demircandemir.relaysample.domain.use_cases.post_user_info

import com.demircandemir.relaysample.data.repository.Repository

class PostUserInfoUseCase(
    private val repository: Repository
) {
    operator fun invoke(id: String) {
        repository.postUserInfo(id = id)
    }
}