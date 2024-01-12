package com.demircandemir.relaysample.presentation.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.paging.compose.LazyPagingItems
import com.demircandemir.relaysample.domain.model.MealInfo
import com.demircandemir.relaysample.presentation.components.FoodCard

@Composable
fun ListContent(
    meals: LazyPagingItems<MealInfo>? = null,
    navController: NavHostController
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(140.dp),
        modifier = Modifier
            .fillMaxHeight(),
        contentPadding = PaddingValues(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(6) {
            FoodCard(
                meal = meals?.get(it)!!,
                navController
            )
        }
    }
}