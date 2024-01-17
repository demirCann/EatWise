package com.demircandemir.relaysample.presentation.screens.meals.breakfast_meals

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import com.demircandemir.relaysample.R
import com.demircandemir.relaysample.navigation.Screens
import com.demircandemir.relaysample.presentation.common.ListContent
import com.demircandemir.relaysample.presentation.screens.meals.MealsTopBar
import com.demircandemir.relaysample.presentation.screens.selection.SelectionScreenViewModel

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun BreakfastMealsScreen(
    selectionScreenViewModel: SelectionScreenViewModel = hiltViewModel(),
    navController: NavHostController
) {

    val repast = selectionScreenViewModel.repast
    selectionScreenViewModel.getMealsForSelection(repast!!)

    val meals = selectionScreenViewModel.meals.collectAsLazyPagingItems()


    Scaffold(
        topBar = {
            MealsTopBar(
                topBarName = repast,
                onSearchClicked = {
                    navController.navigate(Screens.Search.route)
                },
                onBackClicked = {
                    navController.popBackStack()
                }
            )
        },
        content = {
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(it)
            ) {

                ListContent(
                    meals = meals,
                    modifier = Modifier.padding(bottom = 60.dp),
                    navController = navController
                )

            }


        }
    )
}


@Preview
@Composable
fun BreakfastMealsScreenPreview() {
    //BreakfastMealsScreen()
}