// package com.demircandemir.relaysample.presentation.screens.main
//
// import android.annotation.SuppressLint
// import android.graphics.drawable.Icon
// import android.util.Log
// import androidx.compose.material3.ExperimentalMaterial3Api
// import androidx.compose.material3.Icon
// import androidx.compose.material3.NavigationBar
// import androidx.compose.material3.NavigationBarItem
// import androidx.compose.material3.Scaffold
// import androidx.compose.material3.Text
// import androidx.compose.runtime.Composable
// import androidx.compose.runtime.getValue
// import androidx.compose.runtime.mutableStateOf
// import androidx.compose.runtime.saveable.rememberSaveable
// import androidx.compose.runtime.setValue
// import androidx.navigation.NavGraph.Companion.findStartDestination
// import androidx.navigation.NavHostController
// import androidx.navigation.compose.rememberNavController
// import com.demircandemir.relaysample.navigation.bottomBarNavigation.BottomBarScreenItem
// import com.demircandemir.relaysample.navigation.bottomBarNavigation.BottomNavGraph
// import com.demircandemir.relaysample.navigation.graphs.MainNavGraph
//
// @OptIn(ExperimentalMaterial3Api::class)
// @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
// @Composable
// fun MainScreen(
// navController: NavHostController = rememberNavController()
// ) {
//
// Log.d("Screens", "MainScreen: Executed")
//
// Scaffold(
// bottomBar = {
// BottomBar(navController = navController)
// }
// ) {
// MainNavGraph(navController = navController)
// }
// }
//
//
// @Composable
// fun BottomBar(navController: NavHostController) {
// val screens = listOf(
// BottomBarScreenItem.Home,
// BottomBarScreenItem.Recipe,
// BottomBarScreenItem.Progress
// )
//
// var selectedItemIndex by rememberSaveable {
// mutableStateOf(0)
// }
//
//
// NavigationBar {
// screens.forEachIndexed { index, item ->
// NavigationBarItem(
// selected = selectedItemIndex == index,
// onClick = {
// selectedItemIndex = index
// navController.navigate(item.route) {
// popUpTo(navController.graph.startDestinationId)
// launchSingleTop = true
// }
// },
// label = {
// Text(text = item.title)
// },
// icon = {
// Icon(
// imageVector = if (index == selectedItemIndex) {
// item.selectedIcon
// } else item.unselectedIcon,
// contentDescription = item.title
// )
// }
// )
// }
// }
//
// }