package com.demircandemir.relaysample.presentation.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun CalorieCalcBox(
    totalCalorieIntake: Int,
    currentCalorieIntake: Int,
    ) {

    val percentageValue = ((currentCalorieIntake.toFloat() / totalCalorieIntake.toFloat()) * 100).toInt()

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(MaterialTheme.colorScheme.inverseOnSurface),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(),
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Column(
                    modifier = Modifier
                        .weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "$totalCalorieIntake",
                        style = MaterialTheme.typography.bodyLarge,
                    )

                    Text(
                        text = "kcal left",
                        style = MaterialTheme.typography.bodySmall,
                    )
                }


                GaugeGraph(
                    modifier = Modifier
                        .weight(1f)
                        .padding(4.dp),
                    value = percentageValue
                )

                Column(
                    modifier = Modifier
                        .weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "${totalCalorieIntake - currentCalorieIntake} ",
                        style = MaterialTheme.typography.bodyLarge,
                    )

                    Text(
                        text = "kcal eaten",
                        style = MaterialTheme.typography.bodySmall,
                    )
                }

            }

            IngredientsBar()

        }
    }
}



@Preview(showBackground = true)
@Composable
fun CalorieCalcBoxPreview() {
    CalorieCalcBox(1000, 500)
}