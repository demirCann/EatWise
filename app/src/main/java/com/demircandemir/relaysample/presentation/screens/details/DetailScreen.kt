package com.demircandemir.relaysample.presentation.screens.details

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController

@Composable
fun DetailScreen(
    navController: NavHostController,
    detailsViewModel: DetailsViewModel = hiltViewModel()
) {

    val meal = detailsViewModel.mealInfo


    DetailsContent(
        meal = meal!!,
        onFinishedClicked = {
            navController.popBackStack()
        }
    )
}