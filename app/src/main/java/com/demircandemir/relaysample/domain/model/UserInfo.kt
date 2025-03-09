package com.demircandemir.relaysample.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.demircandemir.relaysample.util.Constants.USER_INFO_TABLE
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = USER_INFO_TABLE)
data class UserInfo(
    @PrimaryKey(autoGenerate = false)
    val user_id: String? = null,
    val name: String? = null,
    val goal: String? = null,
    val weight: Int? = null,
    val height: Int? = null,
    val age: Int? = null,
    val gender: String? = null,
    val BMR: Double? = null,
    val calculated_intake: Double? = null,
    val exercise_amount: Int? = null,
    val weight_goal: Int? = null,
    val time_frame: Int? = null,
    val diet_type: String? = null
)