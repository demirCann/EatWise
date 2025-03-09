package com.demircandemir.relaysample.presentation.screens.home

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.demircandemir.relaysample.navigation.Screens

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@ExperimentalMaterial3Api
@Composable
fun HomeScreen(
    navController: NavHostController,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val homeUiState by viewModel.uiState.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            HomeTopBar(
                onChatIconClicked = {
                    navController.navigate(Screens.Chat.route)
                },
                onProfileImageClicked = {
                    navController.navigate(Screens.Profile.route) {
                        popUpTo(Screens.Profile.route) {
                            inclusive = true
                        }
                    }
                }
            )
        },
        content = { paddingValues ->
            HomeContent(
                dailyMealsItemList = homeUiState.meals,
                userInfo = homeUiState.userInfo,
                modifier = Modifier
                    .padding(paddingValues)
                    .padding(bottom = 40.dp)
                    .verticalScroll(rememberScrollState()),
                onDailyMealRowClicked = {
                    navController.navigate(Screens.FoodSelection.passRepast(it.name))
                }
            )
        }
    )
}