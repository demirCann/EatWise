package com.demircandemir.relaysample.presentation.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun IngredientsBar() {

    val progressBarItems = listOf(
        ProgressBarItem(
            name = "Carbs",
            progress = 0.5f,
            intaked = 100,
            total = 200,
        ),
        ProgressBarItem(
            name = "Protein",
            progress = 0.5f,
            intaked = 100,
            total = 200,
        ),
        ProgressBarItem(
            name = "Fat",
            progress = 0.5f,
            intaked = 100,
            total = 200,
        ),
    )

    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly,
    ) {
        progressBarItems.forEach {
            ProgressBar(
                progressBarItem = it
            )
        }
    }
}


@Composable
fun ProgressBar(
    progressBarItem: ProgressBarItem
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = progressBarItem.name,
                style = MaterialTheme.typography.bodySmall,
            )

            LinearProgressIndicator(
                progress = 0.5f,
                modifier = Modifier
                    .width(80.dp)
                    .clip(RoundedCornerShape(2.dp))
            )

            Text(text = "${progressBarItem.intaked} / ${progressBarItem.total} g")
        }
    }
}

data class ProgressBarItem(
    val name: String,
    val progress: Float,
    val intaked: Int,
    val total: Int,
)

@Preview
@Composable
fun IngredientsBarPreview() {
    IngredientsBar()
}

@Preview
@Composable
fun ProgressBarPreview() {
    //ProgressBar()
}