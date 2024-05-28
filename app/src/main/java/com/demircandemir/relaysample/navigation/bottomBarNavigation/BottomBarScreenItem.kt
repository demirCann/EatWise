package com.demircandemir.relaysample.navigation.bottomBarNavigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import com.demircandemir.relaysample.R




sealed class BottomBarScreenItem(
    val route: String,
    val title: String,
    val selectedIcon: Int,
    val unselectedIcon: Int
) {
    data object Home : BottomBarScreenItem(
        route = "home_screen",
        title = "Home",
        selectedIcon = R.drawable.ic_home,
        unselectedIcon = R.drawable.ic_home
    )

    data object Meals : BottomBarScreenItem(
        route = "recipe_screen",
        title = "Meals",
        selectedIcon = R.drawable.ic_hopping_cart,
        unselectedIcon = R.drawable.ic_hopping_cart
    )

    data object Progress : BottomBarScreenItem(
        route = "progress_screen",
        title = "Progress",
        selectedIcon = R.drawable.ic_date_range,
        unselectedIcon = R.drawable.ic_date_range
    )

    data object Chat: BottomBarScreenItem(
        route = "chat_screen",
        title = "Chat",
        selectedIcon = R.drawable.ic_chat,
        unselectedIcon = R.drawable.ic_chat
    )


}

