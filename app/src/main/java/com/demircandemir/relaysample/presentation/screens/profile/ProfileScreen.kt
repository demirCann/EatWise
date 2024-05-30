package com.demircandemir.relaysample.presentation.screens.profile


import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.demircandemir.relaysample.navigation.Screens

@Composable
fun ProfileScreen(
    navController: NavHostController
) {

    ProfileContent(
        onBackClicked = {
            navController.navigate(Screens.Home.route)
        },
        onSettingsClicked = {
            navController.navigate(Screens.Settings.route)
        },
        navController = navController
    )

}




