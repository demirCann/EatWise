package com.demircandemir.relaysample.domain.model


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.demircandemir.relaysample.util.Constants.MEAL_DATABASE
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = MEAL_DATABASE)
data class MealInfo(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val name: String,
    val calorie: String,
    val protein: String,
    val fat: String,
    val carbohydrate: String,
    val mealType: String,
    val recipe: List<String>,
    val image: String
)