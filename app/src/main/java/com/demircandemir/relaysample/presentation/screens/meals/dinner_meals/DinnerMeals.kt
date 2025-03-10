package com.demircandemir.relaysample.presentation.screens.meals.dinner_meals

import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.demircandemir.relaysample.R
import com.demircandemir.relaysample.presentation.screens.meals.MealsTopBar

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun DinnerMealsScreen(
    navController: NavHostController
) {
    Scaffold(
        topBar = {
            MealsTopBar(
                topBarName = stringResource(R.string.dinner),
                onSearchClicked = {},
                onBackClicked = {
                    navController.popBackStack()
                }
            )
        }
    ) {}
}

