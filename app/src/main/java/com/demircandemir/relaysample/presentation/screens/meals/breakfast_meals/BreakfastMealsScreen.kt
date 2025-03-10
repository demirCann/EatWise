//package com.demircandemir.relaysample.presentation.screens.meals.breakfast_meals
//
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.fillMaxHeight
//import androidx.compose.foundation.layout.padding
//import androidx.compose.material3.Scaffold
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.LaunchedEffect
//import androidx.compose.runtime.getValue
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.unit.dp
//import androidx.hilt.navigation.compose.hiltViewModel
//import androidx.lifecycle.compose.collectAsStateWithLifecycle
//import androidx.navigation.NavHostController
//import androidx.paging.compose.collectAsLazyPagingItems
//import com.demircandemir.relaysample.navigation.Screens
//import com.demircandemir.relaysample.presentation.common.ListContent
//import com.demircandemir.relaysample.presentation.screens.meals.MealsTopBar
//import com.demircandemir.relaysample.presentation.screens.selection.SelectionScreenViewModel
//
//@Composable
//fun BreakfastMealsScreen(
//    navController: NavHostController,
//    selectionScreenViewModel: SelectionScreenViewModel = hiltViewModel()
//) {
//    val uiState by selectionScreenViewModel.selectionUiState.collectAsStateWithLifecycle()
//    val repast = uiState.repast ?: ""
//    val meals = uiState.meals.collectAsLazyPagingItems()
//
//    LaunchedEffect(Unit) {
//        selectionScreenViewModel.getMealsForSelection(repast)
//    }
//
//    Scaffold(
//        topBar = {
//            MealsTopBar(
//                topBarName = repast,
//                onSearchClicked = {
//                    navController.navigate(Screens.Search.route)
//                },
//                onBackClicked = {
//                    navController.popBackStack()
//                }
//            )
//        },
//        content = {
//            Column(
//                modifier = Modifier
//                    .fillMaxHeight()
//                    .padding(it)
//            ) {
//                ListContent(
//                    meals = meals,
//                    isSelectionScreen = false,
//                    modifier = Modifier.padding(bottom = 60.dp),
//                    navController = navController
//                )
//            }
//        }
//    )
//}