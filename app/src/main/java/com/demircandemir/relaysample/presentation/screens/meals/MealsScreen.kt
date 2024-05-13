package com.demircandemir.relaysample.presentation.screens.meals

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.demircandemir.relaysample.navigation.Screens
import com.demircandemir.relaysample.presentation.common.ListContent

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MealsScreen(
    navController: NavHostController,
    mealsViewModel: MealsViewModel = hiltViewModel()
) {
    val allMeals = mealsViewModel.allMeals.collectAsLazyPagingItems()

    Scaffold(
        topBar = {
            MealsTopBar(
                topBarName = "Meals",
                onSearchClicked = { navController.navigate(Screens.Search.route) },
                onBackClicked = {}
            )
        }
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
        ) {

            Carousel(
                navController = navController,
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            ListContent(
                meals = allMeals,
                navController = navController
            )
        }
    }
}



@Preview(showBackground = true)
@Composable
fun PreviewRecipeScreen() {
    MealsScreen(
        navController = rememberNavController()
    )
}


