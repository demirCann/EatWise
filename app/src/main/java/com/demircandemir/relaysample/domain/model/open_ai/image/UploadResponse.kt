package com.demircandemir.relaysample.domain.model.open_ai.image

import com.google.gson.annotations.SerializedName

data class UploadResponse(
    @SerializedName("id") val id: String,
    @SerializedName("object") val objectType: String,
    @SerializedName("created") val created: Long,
    @SerializedName("url") val url: String,
    @SerializedName("description") val description: String,
    @SerializedName("status") val status: String
)

