package com.demircandemir.relaysample.presentation.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.demircandemir.relaysample.domain.model.MealInfo
import com.demircandemir.relaysample.presentation.components.FoodCard
import com.demircandemir.relaysample.presentation.components.ShimmerEffect

@Composable
fun ListContent(
    //meals: LazyPagingItems<MealInfo>,
    navController: NavHostController
) {
    /*
    val result = handlePagingResult(
        meals = meals
    )

    if (result) {
        LazyVerticalGrid(
            columns = GridCells.Adaptive(140.dp),
            modifier = Modifier
                .fillMaxHeight(),
            contentPadding = PaddingValues(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(
                count = meals.itemCount,
            ) {
                FoodCard(
                    meal = meals[it],
                    navController
                )
            }
        }


    }


     */

    LazyVerticalGrid(
        columns = GridCells.Adaptive(140.dp),
        modifier = Modifier
            .fillMaxHeight(),
        contentPadding = PaddingValues(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(6) {
            FoodCard(navController)
        }
    }
}


@Composable
fun handlePagingResult(
    meals: LazyPagingItems<MealInfo>,
): Boolean {
    meals.apply {
        val error = when {
            loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
            loadState.append is LoadState.Error -> loadState.append as LoadState.Error
            loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
            else -> null
        }
        return when {
            loadState.refresh is LoadState.Loading -> {
                ShimmerEffect()
                false
            }
            error != null -> {
                EmptyScreen(error = error, meals = meals)
                false
            }
            meals.itemCount < 1 -> {
                EmptyScreen()
                false
            }
            else -> true
        }
    }
}