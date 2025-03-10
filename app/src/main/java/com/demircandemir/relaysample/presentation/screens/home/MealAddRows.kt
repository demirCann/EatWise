package com.demircandemir.relaysample.presentation.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.demircandemir.relaysample.R
import com.demircandemir.relaysample.domain.model.DailyMealsItem

@Composable
fun MealAddRows(
    dailyMealsItemList: List<DailyMealsItem>,
    onDailyMealRowClicked: (dailyMealsItem: DailyMealsItem) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp, bottom = 48.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        dailyMealsItemList.forEach { item ->
            MealAddRowItem(
                mealAddRowItem = item,
                onDailyMealRowClicked = { onDailyMealRowClicked(item) }
            )
        }
    }
}

@Composable
fun MealAddRowItem(
    mealAddRowItem: DailyMealsItem,
    onDailyMealRowClicked: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(MaterialTheme.colorScheme.tertiaryContainer)
            .clickable {
                onDailyMealRowClicked()
            },
        contentAlignment = Alignment.CenterStart
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = mealAddRowItem.imageRes),
                contentDescription = stringResource(R.string.add_meal_row),
                modifier = Modifier
                    .padding(8.dp)
                    .weight(1f)
                    .clip(CircleShape)
            )
            Column(
                modifier = Modifier
                    .padding(start = 8.dp)
                    .weight(4f)
            ) {
                Text(
                    text = mealAddRowItem.name,
                    style = MaterialTheme.typography.bodyLarge,
                )

                if (mealAddRowItem.meals.isNotEmpty()) {
                    Spacer(modifier = Modifier.height(8.dp))
                    mealAddRowItem.meals.forEach { mealName ->
                        Text(
                            text = mealName.name,
                            style = MaterialTheme.typography.bodySmall,
                            fontWeight = FontWeight.Light
                        )
                    }
                }
            }
            Icon(
                modifier = Modifier
                    .size(36.dp)
                    .weight(1f),
                imageVector = Icons.Filled.AddCircle,
                contentDescription = stringResource(R.string.add_button)
            )
        }
    }
}