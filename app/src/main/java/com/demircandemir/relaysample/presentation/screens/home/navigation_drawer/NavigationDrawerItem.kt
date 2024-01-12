// package com.demircandemir.relaysample.presentation.screens.home.navigation_drawer
//
// import androidx.compose.material.icons.Icons
// import androidx.compose.material.icons.filled.DateRange
// import androidx.compose.material.icons.filled.Home
// import androidx.compose.material.icons.filled.Person
// import androidx.compose.material.icons.filled.Settings
// import androidx.compose.material.icons.filled.ShoppingCart
// import androidx.compose.material.icons.outlined.DateRange
// import androidx.compose.material.icons.outlined.Home
// import androidx.compose.material.icons.outlined.Person
// import androidx.compose.material.icons.outlined.Settings
// import androidx.compose.material.icons.outlined.ShoppingCart
// import androidx.compose.ui.graphics.vector.ImageVector
// import com.demircandemir.relaysample.navigation.bottomBarNavigation.BottomBarScreenItem
//
// sealed class NavigationDrawerItem(
// val route: String,
// val title: String,
// val selectedIcon: ImageVector,
// val unselectedIcon: ImageVector
// ) {
//
// object Home : NavigationDrawerItem(
// route = "home_screen",
// title = "Home",
// selectedIcon = Icons.Filled.Home,
// unselectedIcon = Icons.Outlined.Home
// )
//
// object Recipe : NavigationDrawerItem(
// route = "recipe_screen",
// title = "Recipes",
// selectedIcon = Icons.Filled.ShoppingCart,
// unselectedIcon = Icons.Outlined.ShoppingCart
// )
//
// object Progress : NavigationDrawerItem(
// route = "progress_screen",
// title = "Progress",
// selectedIcon = Icons.Filled.DateRange,
// unselectedIcon = Icons.Outlined.DateRange
// )
//
// object Profile : NavigationDrawerItem(
// route = "profile_screen",
// title = "Profile",
// selectedIcon = Icons.Filled.Person,
// unselectedIcon = Icons.Outlined.Person
// )
//
// object Settings : NavigationDrawerItem(
// route = "settings_screen",
// title = "Settings",
// selectedIcon = Icons.Filled.Settings,
// unselectedIcon = Icons.Outlined.Settings
// )