package com.demircandemir.relaysample.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class MealsRequest(
    val meals: List<Int>
)
