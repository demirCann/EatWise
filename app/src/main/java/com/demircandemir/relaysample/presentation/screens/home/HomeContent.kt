package com.demircandemir.relaysample.presentation.screens.home

import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Composable
fun HomeContent(
    modifier: Modifier,
    navController: NavHostController
) {



    Box(
        modifier = modifier
            .fillMaxWidth()
    ){

        Column(){
            CalorieCalcBox(
                totalCalorieIntake = 2000,
                currentCalorieIntake = 100,
            )

            Spacer(modifier = Modifier.height(16.dp))

            MealAddRows(navController = navController)
        }




    }


}


@Preview(showBackground = true)
@Composable
fun HomeContentPreview() {
    HomeContent(
        modifier = Modifier,
        navController = rememberNavController()
    )
}

