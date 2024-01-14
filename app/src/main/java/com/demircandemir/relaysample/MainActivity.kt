package com.demircandemir.relaysample

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.demircandemir.relaysample.navigation.Screens
import com.demircandemir.relaysample.navigation.SetupNavGraph
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
                    Screens.Home.route, Screens.Recipe.route, Screens.Progress.route -> {
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
                }






            
            /*Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()

                    NavHost(navController = navController, startDestination = "splash_screen") {

                        composable("splash_screen") {
                            SplashScreen(navController = navController)
                        }

                        composable("login_screen") {
                            Log.d("Screens", "SignInComposable: Executed")

                            val viewModel = viewModel<SignInViewModel>()
                            val state by viewModel.state.collectAsStateWithLifecycle()


                            if (googleAuthUiClient.getSignedInUser() != null) {
                                LaunchedEffect(key1 = Unit){
                                    Log.d("Screens", "Launched Effect launched")
                                    navController.navigate("main_screen")
                                }
                            } else {
                                val launcher = rememberLauncherForActivityResult(
                                    contract = ActivityResultContracts.StartIntentSenderForResult(),
                                    onResult = { result ->
                                        Log.d("Screens", "onLaunch: $result")
                                        if(result.resultCode == RESULT_OK) {
                                            lifecycleScope.launch {
                                                val signInResult = googleAuthUiClient.signInWithIntent(
                                                    intent = result.data ?: return@launch
                                                )
                                                viewModel.onSignInResult(signInResult)
                                            }
                                        }
                                    }
                                )

                                LaunchedEffect(key1 = state.isSignInSuccessful) {
                                    if(state.isSignInSuccessful) {
                                        Toast.makeText(
                                            applicationContext,
                                            "Sign in successful",
                                            Toast.LENGTH_LONG
                                        ).show()

                                        Log.d("Screens", "Success Launched Effect launched")
                                        navController.navigate("main_screen")

                                    }
                                }

                                Log.d("Screens", "onCreate: $state")

                                SignInScreen(
                                    state = state,
                                    onSignInClick = {
                                        lifecycleScope.launch {
                                            Log.d("Screens", "SignScreen: Executed in MainActivity")
                                            val signInIntentSender = googleAuthUiClient.signIn()
                                            launcher.launch(
                                                IntentSenderRequest.Builder(
                                                    signInIntentSender ?: return@launch
                                                ).build()
                                            )
                                        }
                                    }
                                )
                            }


                            /*
                            LaunchedEffect(key1 = Unit){
                                Log.d("Screens", "First Launched Effect entered")
                                if(googleAuthUiClient.getSignedInUser() != null) {
                                    Log.d("Screens", "First Launched Effect launched")
                                    navController.navigate("main_screen")
                                }
                            }


                            val launcher = rememberLauncherForActivityResult(
                                contract = ActivityResultContracts.StartIntentSenderForResult(),
                                onResult = { result ->
                                    Log.d("Screens", "onLaunch: $result")
                                    if(result.resultCode == RESULT_OK) {
                                        lifecycleScope.launch {
                                            val signInResult = googleAuthUiClient.signInWithIntent(
                                                intent = result.data ?: return@launch
                                            )
                                            viewModel.onSignInResult(signInResult)
                                        }
                                    }
                                }
                            )

                            LaunchedEffect(key1 = state.isSignInSuccessful) {
                                if(state.isSignInSuccessful) {
                                    Toast.makeText(
                                        applicationContext,
                                        "Sign in successful",
                                        Toast.LENGTH_LONG
                                    ).show()

                                    Log.d("Screens", "Success Launched Effect launched")
                                    navController.navigate("main_screen")

                                }
                            }

                            Log.d("Screens", "onCreate: $state")

                            SignInScreen(
                                state = state,
                                onSignInClick = {
                                    lifecycleScope.launch {
                                        Log.d("Screens", "SignScreen: Executed in MainActivity")
                                        val signInIntentSender = googleAuthUiClient.signIn()
                                        launcher.launch(
                                            IntentSenderRequest.Builder(
                                                signInIntentSender ?: return@launch
                                            ).build()
                                        )
                                    }
                                }
                            )*/
                        }
                        composable("main_screen") {
                            Log.d("Screens", "MainScreenComposable: Executed")
                            MainScreen()
                        }

                    }
                }*/
            }
        }
    }
}




