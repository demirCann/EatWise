package com.demircandemir.relaysample.presentation.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.demircandemir.relaysample.navigation.Screens
import com.demircandemir.relaysample.navigation.bottomBarNavigation.BottomBarScreenItem

@Composable
fun BottomBar(
    navController: NavHostController,
    bottomBarState: MutableState<Boolean>
) {
    val screens = listOf(
        BottomBarScreenItem.Home,
        BottomBarScreenItem.Meals,
        BottomBarScreenItem.Favorites
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
                    },
                    icon = {
                        Icon(
                            painter = painterResource(screen.icon),
                            contentDescription = screen.title
                        )
                    },
                    label = { Text(text = screen.title) },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = MaterialTheme.colorScheme.primary,
                        selectedTextColor = MaterialTheme.colorScheme.primary,
                        indicatorColor = Color.Transparent
                    )
                )
            }
        }
    }
}

@Composable
fun HandleBottomBarVisibility(
    navController: NavHostController,
    bottomBarState: MutableState<Boolean>
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    
    when (navBackStackEntry?.destination?.route) {
        Screens.Home.route, Screens.MealList.route -> {
            bottomBarState.value = true
        }
        else -> {
            bottomBarState.value = false
        }
    }
} 