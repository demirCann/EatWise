package com.demircandemir.relaysample.presentation.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.demircandemir.relaysample.domain.model.MealInfo
import com.demircandemir.relaysample.presentation.components.SelectableFoodCard
import com.demircandemir.relaysample.presentation.components.ShimmerEffect

@Composable
fun ListContent(
    meals: LazyPagingItems<MealInfo>,
    isSelectionScreen: Boolean,
   // onAddedClicked: (Int) -> Unit,
    modifier: Modifier = Modifier,
    navController: NavHostController
) {


    val result = handlePagingResult(meals = meals)

    if (result) {
        LazyVerticalGrid(
            columns = GridCells.Adaptive(140.dp),
            modifier = modifier
                .fillMaxHeight(),
            contentPadding = PaddingValues(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(count = meals.itemCount) {
                meals[it]?.let { it1 ->
                    SelectableFoodCard(
                        meal = it1,
                        isSelectionScreen = isSelectionScreen,
                        navController = navController
                    )
                }
            }
        }
    }


}


@Composable
fun handlePagingResult(
    meals: LazyPagingItems<MealInfo>
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