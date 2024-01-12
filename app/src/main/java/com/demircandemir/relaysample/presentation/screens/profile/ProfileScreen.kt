package com.demircandemir.relaysample.presentation.screens.profile

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.TopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
        }
    )

}




