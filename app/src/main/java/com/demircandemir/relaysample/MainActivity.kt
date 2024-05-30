package com.demircandemir.relaysample

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.demircandemir.relaysample.navigation.Screens
import com.demircandemir.relaysample.navigation.SetupNavGraph
import com.demircandemir.relaysample.presentation.screens.chat.ChatScreen
import com.demircandemir.relaysample.presentation.screens.home.BottomBar
import com.demircandemir.relaysample.ui.theme.RelaySampleTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var navController: NavHostController


    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RelaySampleTheme() {

                val bottomBarState = rememberSaveable {
                    mutableStateOf(false)
                }

                navController = rememberNavController()

                val navBackStackEntry by navController.currentBackStackEntryAsState()

                when (navBackStackEntry?.destination?.route) { // Current route
                    Screens.Home.route, Screens.Recipe.route, Screens.ChatScreen.route -> {
                        bottomBarState.value = true
                    }
                    else -> {
                        bottomBarState.value = false
                    }
                }

                Scaffold(
                    bottomBar = {
                        BottomBar(navController = navController, bottomBarState = bottomBarState)
                    }


                ) {
                    SetupNavGraph(scope = lifecycleScope, navController = navController)

                    //ChatScreen()
                }
            }
        }
    }
}




