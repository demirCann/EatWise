package com.demircandemir.relaysample.presentation.screens.home

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.demircandemir.relaysample.navigation.Screens
import com.demircandemir.relaysample.navigation.bottomBarNavigation.BottomBarScreenItem



@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@ExperimentalMaterial3Api
@Composable
fun HomeScreen(
    navController: NavHostController
) {
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
        content = { paddingValues ->
            HomeContent(
                modifier = Modifier
                    .padding(paddingValues)
                    .padding(bottom = 40.dp)
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
        BottomBarScreenItem.Meals,
        BottomBarScreenItem.Progress
    )

    AnimatedVisibility(
        visible = bottomBarState.value,
    ) {
        NavigationBar(
            containerColor = MaterialTheme.colorScheme.surface
        ) {
            val navBackStackEntry by navController.currentBackStackEntryAsState() // Shows top element of the back stack and it will recompose if the navController.navigate or navController.popBackStack is called
            val currentRoute = navBackStackEntry?.destination?.route

            screens.forEach { screen ->
                NavigationBarItem(
                    icon = {
                        Icon(
                            imageVector = if (currentRoute == screen.route) screen.selectedIcon else screen.unselectedIcon,
                            contentDescription = screen.title
                        )
                    },
                    label = { Text(text = screen.title) },
                    selected = currentRoute == screen.route,
                    onClick = {
                        navController.navigate(screen.route) {
                            navController.graph.startDestinationRoute?.let { route ->
                                popUpTo(route) {
                                    saveState = true
                                }
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
            }
        }
    }
}








