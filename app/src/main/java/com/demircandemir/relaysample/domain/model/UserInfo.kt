package com.demircandemir.relaysample.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.demircandemir.relaysample.util.Constants.USER_INFO_TABLE
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = USER_INFO_TABLE)
data class UserInfo(
    @PrimaryKey(autoGenerate = false)
    val user_id: String,
    val name: String,
    val goal: String,
    val weight: Int,
    val height: Int,
    val age: Int,
    val gender: String,
    val BMR: Double,
    val calculated_intake: Double,
    val exercise_amount: Int,
    val weight_goal: Int,
    val time_frame: Int,
    val diet_type: String
)
