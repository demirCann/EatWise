package com.demircandemir.relaysample.presentation.screens.search

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import com.demircandemir.relaysample.presentation.common.ListContent

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SearchScreen(
    navController: NavHostController,
    searchViewModel: SearchViewModel = hiltViewModel()
) {

    val searchQuery by searchViewModel.searchQuery
    val searchedMeals = searchViewModel.searchedMeals.collectAsLazyPagingItems()

    Scaffold(
        topBar = {
            SearchTopAppBar(
                text = searchQuery,
                onTextChange = { searchViewModel.updateSearchQuery(it) },
                onClosedClicked = { navController.popBackStack() },
                onSearchClicked = { searchViewModel.searchMeals(it) }
            )
        },
        content = { paddingValues ->
            ListContent(
                searchedMeals,
                isSelectionScreen = false,
                modifier = Modifier.padding(paddingValues),
                navController = navController
            )
        }
    )
}