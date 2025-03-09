package com.demircandemir.relaysample.presentation.screens.home

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.demircandemir.relaysample.R

@Composable
fun DonutChartBox(
    totalCalorieIntake: Int,
    viewModel: HomeViewModel = hiltViewModel()
) {

    val uiState by viewModel.uiState.collectAsState()
    val totalCalories = uiState.totalCalories
    val totalCarbohydrates = uiState.totalCarbohydrates
    val totalProteins = uiState.totalProteins
    val totalFats = uiState.totalFats

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(MaterialTheme.colorScheme.surfaceVariant),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(),
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Column(
                    modifier = Modifier
                        .weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "${totalCalorieIntake - totalCalories}",
                        style = MaterialTheme.typography.bodyLarge,
                    )

                    Text(
                        text = stringResource(R.string.kcal_left),
                        style = MaterialTheme.typography.bodySmall,
                    )
                }


                DonutChart(
                    caloriesLeft = (totalCalorieIntake - totalCalories).toFloat(),
                    caloriesEaten = totalCalories.toFloat(),
                    totalCalories = totalCalorieIntake.toFloat(),
                    modifier = Modifier
                        .size(100.dp)
                        .padding(8.dp)
                )

                Column(
                    modifier = Modifier
                        .weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "$totalCalories",
                        style = MaterialTheme.typography.bodyLarge,
                    )

                    Text(
                        text = stringResource(R.string.kcal_eaten),
                        style = MaterialTheme.typography.bodySmall,
                    )
                }

            }

            Spacer(modifier = Modifier.height(16.dp))

            IngredientsBar(
                totalCarbohydrates, totalProteins, totalFats
            )
        }
    }
}

@Composable
fun DonutChart(
    modifier: Modifier = Modifier,
    caloriesLeft: Float,
    caloriesEaten: Float,
    totalCalories: Float
) {
    val caloriesLeftPercentage = caloriesLeft / totalCalories
    val caloriesEatenPercentage = caloriesEaten / totalCalories

    Canvas(modifier = modifier) {
        val innerRadius = size.minDimension / 2.5f
        val outerRadius = size.minDimension / 2.0f

        drawArc(
            color = Color(0xFF43A047), // Green
            startAngle = 0f,
            sweepAngle = 360 * caloriesEatenPercentage,
            useCenter = false,
            topLeft = Offset((size.width - outerRadius * 2) / 2, (size.height - outerRadius * 2) / 2),
            size = Size(outerRadius * 2, outerRadius * 2),
            style = Stroke(width = innerRadius)
        )

        drawArc(
            color = Color(0xFFF44336), // Red
            startAngle = 360 * caloriesEatenPercentage,
            sweepAngle = 360 * caloriesLeftPercentage,
            useCenter = false,
            topLeft = Offset((size.width - outerRadius * 2) / 2, (size.height - outerRadius * 2) / 2),
            size = Size(outerRadius * 2, outerRadius * 2),
            style = Stroke(width = innerRadius)
        )
    }
}