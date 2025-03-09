package com.demircandemir.relaysample.presentation.screens.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.demircandemir.relaysample.domain.model.DailyMealsItem
import com.demircandemir.relaysample.domain.model.UserInfo

@Composable
fun HomeContent(
    dailyMealsItemList: List<DailyMealsItem>,
    userInfo: UserInfo,
    modifier: Modifier,
    onDailyMealRowClicked: (dailyMealsItem: DailyMealsItem) -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Column {

            userInfo.calculated_intake?.let { DonutChartBox(totalCalorieIntake = it.toInt()) }

            Spacer(modifier = Modifier.height(64.dp))

            MealAddRows(
                dailyMealsItemList = dailyMealsItemList,
                onDailyMealRowClicked = onDailyMealRowClicked
            )
        }
    }
}