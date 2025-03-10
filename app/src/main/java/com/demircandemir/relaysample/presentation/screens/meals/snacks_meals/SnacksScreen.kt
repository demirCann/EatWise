package com.demircandemir.relaysample.presentation.screens.meals.snacks_meals

import android.annotation.SuppressLint
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.demircandemir.relaysample.R
import com.demircandemir.relaysample.navigation.Screens
import com.demircandemir.relaysample.presentation.screens.meals.MealsTopBar

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SnacksMealsScreen(
    navController: NavHostController
) {
    Scaffold(
        topBar = {
            MealsTopBar(
                topBarName = stringResource(R.string.snacks),
                onSearchClicked = {},
                onBackClicked = {
                    navController.popBackStack()
                }
            )
        }
    ) {}
}