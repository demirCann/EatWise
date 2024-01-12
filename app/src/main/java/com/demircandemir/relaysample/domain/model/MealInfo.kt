package com.demircandemir.relaysample.domain.model

import androidx.compose.ui.graphics.vector.ImageVector
import kotlinx.serialization.Serializable

@Serializable
data class MealInfo(
    val name: String,
    val calorie: String,
    val protein: String,
    val fat: String,
    val carbohydrate: String,
    val mealType: String,
    val recipe: List<String>,
    val imageVector: String
) {
}