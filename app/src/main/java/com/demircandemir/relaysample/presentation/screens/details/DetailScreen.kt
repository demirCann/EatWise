package com.demircandemir.relaysample.presentation.screens.details

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController

@Composable
fun DetailScreen(
    navController: NavHostController
) {
    DetailsContent(
        onFinishedClicked = {
            navController.popBackStack()
        }
    )
}