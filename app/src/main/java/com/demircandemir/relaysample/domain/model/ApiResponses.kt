package com.demircandemir.relaysample.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class ApiResponses(
    val success: Boolean,
    val message: String? = null,
    val userInfo: UserInfo
)