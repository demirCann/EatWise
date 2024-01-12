// package com.demircandemir.relaysample.presentation.screens.home.navigation_drawer
//
// import android.annotation.SuppressLint
// import android.util.Log
// import androidx.compose.foundation.layout.Spacer
// import androidx.compose.foundation.layout.height
// import androidx.compose.foundation.layout.padding
// import androidx.compose.material.Scaffold
// import androidx.compose.material3.DrawerValue
// import androidx.compose.material3.rememberDrawerState
// import androidx.compose.material3.ExperimentalMaterial3Api
// import androidx.compose.material3.Icon
// import androidx.compose.material3.MaterialTheme
// import androidx.compose.material3.ModalDrawerSheet
// import androidx.compose.material3.ModalNavigationDrawer
// import androidx.compose.material3.NavigationDrawerItem
// import androidx.compose.material3.NavigationDrawerItemDefaults
// import androidx.compose.material3.Text
// import androidx.compose.runtime.Composable
// import androidx.compose.runtime.getValue
// import androidx.compose.runtime.mutableStateOf
// import androidx.compose.runtime.rememberCoroutineScope
// import androidx.compose.runtime.saveable.rememberSaveable
// import androidx.compose.runtime.setValue
// import androidx.compose.ui.Modifier
// import androidx.compose.ui.unit.dp
// import androidx.navigation.NavHostController
// import androidx.navigation.compose.rememberNavController
// import com.demircandemir.relaysample.navigation.drawerNavigation.NavigationDrawerNavGraph
// import com.demircandemir.relaysample.presentation.screens.home.HomeContent
// import com.demircandemir.relaysample.presentation.screens.home.HomeTopBar
// import com.google.accompanist.systemuicontroller.rememberSystemUiController
// import kotlinx.coroutines.launch
//
//
// @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
// @OptIn(ExperimentalMaterial3Api::class)
// @Composable
// fun NavigationDrawerFun(
// navController: NavHostController
// ) {
//
//
// Log.d("Screens", "NavigationDrawerFun: Executed")
//
//
// val items = listOf(
// NavigationDrawerItem.Home,
// NavigationDrawerItem.Recipe,
// NavigationDrawerItem.Progress,
// NavigationDrawerItem.Profile,
// NavigationDrawerItem.Settings
// )
//
// val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
// var selectedItemIndex by rememberSaveable {
// mutableStateOf(0)
// }
// val scope = rememberCoroutineScope()
//
// ModalNavigationDrawer(
// drawerContent = {
// ModalDrawerSheet {
// Spacer(modifier = Modifier.height(16.dp))
// items.forEachIndexed { index, item ->
// NavigationDrawerItem(
// label = {
// Text(text = item.title)
// },
// selected = index == selectedItemIndex,
// onClick = {
// Log.d("NavigationDrawerFun", "NavigationDrawerFun: OnClick")
// navController.navigate(item.route)
// selectedItemIndex = index
// scope.launch { drawerState.close() }
// },
// icon = {
// Icon(
// imageVector = if (index == selectedItemIndex) item.selectedIcon else item.unselectedIcon,
// contentDescription = item.title
// )
// },
// modifier = Modifier
// .padding(NavigationDrawerItemDefaults.ItemPadding)
// )
// }
// }
// },
// drawerState = drawerState,
// ) {
//
// NavigationDrawerNavGraph(navController = navController)
//
// val systemUiController = rememberSystemUiController()
// systemUiController.setStatusBarColor(
// color = MaterialTheme.colorScheme.surface
// )
// Scaffold(
// topBar = {
// HomeTopBar(
// onNavigationDrawerClicked = {
// Log.d("HomeScreen", "HomeScreen: onNavigationDrawerClicked")
// scope.launch { drawerState.open() }
// },
// onProfileImageClicked = {
// navController.navigate("profile_screen")
// }
// )
// },
// content = {
// HomeContent()
// }
// )
// }