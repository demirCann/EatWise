package com.demircandemir.relaysample.presentation.screens.home

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.demircandemir.relaysample.navigation.Screens
import com.demircandemir.relaysample.navigation.bottomBarNavigation.BottomBarScreenItem



@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@ExperimentalMaterial3Api
@Composable
fun HomeScreen(
    navController: NavHostController
) {

    val bottomBarState = rememberSaveable {
        mutableStateOf(true)
    }

    Scaffold(
        topBar = {
            HomeTopBar(
                onProfileImageClicked = {
                    navController.navigate(Screens.Profile.route) {
                        popUpTo(Screens.Profile.route) {
                            inclusive = true
                        }
                    }
                }
            )
        },
        bottomBar = {
            BottomBar(navController = navController, bottomBarState = bottomBarState)
        },
        content = { paddingValues ->
            HomeContent(
                modifier = Modifier
                    .padding(paddingValues)
                    .verticalScroll(rememberScrollState()),
                navController = navController
            )
        }
    )

}

@Composable
fun BottomBar(
    navController: NavHostController,
    bottomBarState: MutableState<Boolean>
) {
    val screens = listOf(
        BottomBarScreenItem.Home,
        BottomBarScreenItem.Recipe,
        BottomBarScreenItem.Progress
    )

    AnimatedVisibility(
        visible = bottomBarState.value,
        enter = slideInVertically(initialOffsetY = { it }),
        exit = slideOutVertically(targetOffsetY = { it }),
        content = {

            var selectedItemIndex by rememberSaveable {
                mutableStateOf(0)
            }

            NavigationBar {
                screens.forEachIndexed { index, item ->

                    NavigationBarItem(
                        selected = selectedItemIndex == index,
                        onClick = {
                            Log.d("HomeScreen", "itemName: ${item.title}")

                            selectedItemIndex = index
                            navController.navigate(item.route) {
                                navController.graph.startDestinationRoute?.let { route ->
                                    popUpTo(route) {
                                        saveState = true
                                    }
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        label = {
                            Text(text = item.title)
                        },
                        icon = {
                            Icon(
                                imageVector = if (index == selectedItemIndex) {
                                    item.selectedIcon
                                } else item.unselectedIcon,
                                contentDescription = item.title
                            )
                        }
                    )
                }
            }
        }
    )
}

