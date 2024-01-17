package com.demircandemir.relaysample.presentation.screens.details

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController

@Composable
fun DetailScreen(
    navController: NavHostController,
    detailsViewModel: DetailsViewModel = hiltViewModel()
) {

    val selectedMeal by detailsViewModel.selectedMeal.collectAsState()


    selectedMeal?.let {
        DetailsContent(
        meal = it,
        onFinishedClicked = {
            navController.popBackStack()
        }
    )
    }
}