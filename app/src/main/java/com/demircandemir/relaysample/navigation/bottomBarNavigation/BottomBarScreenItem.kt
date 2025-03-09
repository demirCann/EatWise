package com.demircandemir.relaysample.navigation.bottomBarNavigation

import com.demircandemir.relaysample.R
import com.demircandemir.relaysample.navigation.Screens

sealed class BottomBarScreenItem(
    val route: String,
    val title: String,
    val icon: Int
) {
    data object Home : BottomBarScreenItem(
        route = Screens.Home.route,
        title = Screens.Home.name,
        icon = R.drawable.ic_bt_nav_home
    )

    data object Meals : BottomBarScreenItem(
        route = Screens.MealList.route,
        title = Screens.MealList.name,
        icon = R.drawable.ic_bt_nav_meals
    )

    data object Favorites : BottomBarScreenItem(
        route = Screens.FavoriteMeals.route,
        title = Screens.FavoriteMeals.name,
        icon = R.drawable.ic_bt_nav_favorites
    )
}