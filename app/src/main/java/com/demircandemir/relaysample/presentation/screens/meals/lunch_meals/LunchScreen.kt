package com.demircandemir.relaysample.presentation.screens.meals.lunch_meals

import android.annotation.SuppressLint
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import com.demircandemir.relaysample.R
import com.demircandemir.relaysample.navigation.Screens
import com.demircandemir.relaysample.presentation.screens.meals.MealsTopBar

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LunchMealsScreen(
    navController: NavHostController
) {
    Scaffold(
        topBar = {
            MealsTopBar(
                topBarName = stringResource(R.string.lunch),
                onSearchClicked = {
                    navController.navigate(Screens.Search.route)
                },
                onBackClicked = {
                    navController.popBackStack()
                }
            )
        }
    ) {

    }
}

