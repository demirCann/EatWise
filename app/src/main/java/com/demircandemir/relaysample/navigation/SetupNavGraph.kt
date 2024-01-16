package com.demircandemir.relaysample.navigation

import android.app.Activity.RESULT_OK
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.demircandemir.relaysample.presentation.screens.details.DetailScreen
import com.demircandemir.relaysample.presentation.screens.home.HomeScreen
import com.demircandemir.relaysample.presentation.screens.login.GoogleAuthUiClient
import com.demircandemir.relaysample.presentation.screens.login.SignInScreen
import com.demircandemir.relaysample.presentation.screens.login.SignInViewModel
import com.demircandemir.relaysample.presentation.screens.profile.ProfileScreen
import com.demircandemir.relaysample.presentation.screens.progress.ProgressScreen
import com.demircandemir.relaysample.presentation.screens.meals.MealsScreen
import com.demircandemir.relaysample.presentation.screens.meals.breakfast_meals.BreakfastMealsScreen
import com.demircandemir.relaysample.presentation.screens.meals.dinner_meals.DinnerMealsScreen
import com.demircandemir.relaysample.presentation.screens.meals.lunch_meals.LunchMealsScreen
import com.demircandemir.relaysample.presentation.screens.meals.snacks_meals.SnacksMealsScreen
import com.demircandemir.relaysample.presentation.screens.search.SearchScreen
import com.demircandemir.relaysample.presentation.screens.selection.FoodSelectionScreen
import com.demircandemir.relaysample.presentation.screens.settings.SettingsScreen
import com.demircandemir.relaysample.presentation.screens.splash.SplashScreen
import com.demircandemir.relaysample.presentation.screens.survey.SurveyRoute
import com.demircandemir.relaysample.util.Constants.MEAL_ID_ARGUMENT_KEY
import com.google.android.gms.auth.api.identity.Identity
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SetupNavGraph(
    scope: LifecycleCoroutineScope,
    navController: NavHostController
) {
    val context = LocalContext.current

    val googleAuthUiClient by lazy {
        GoogleAuthUiClient(
            context = context.applicationContext,
            oneTapClient = Identity.getSignInClient(context.applicationContext)
        )
    }

    NavHost(
        navController = navController,
        startDestination = Screens.Home.route
    ) {
        composable(Screens.Splash.route) {
            SplashScreen(navController = navController)
        }

        composable(Screens.Login.route) {
            Log.d("Screens", "SignInComposable: Executed")

            val viewModel = viewModel<SignInViewModel>()
            val state by viewModel.state.collectAsStateWithLifecycle()


            if (googleAuthUiClient.getSignedInUser() != null) {
                LaunchedEffect(key1 = Unit) {
                    Log.d("Screens", "Launched Effect launched")
                    navController.navigate(Screens.Home.route)
                }
            } else {
                val launcher = rememberLauncherForActivityResult(
                    contract = ActivityResultContracts.StartIntentSenderForResult(),
                    onResult = { result ->
                        Log.d("Screens", "onLaunch: $result")
                        if (result.resultCode == RESULT_OK) {
                            scope.launch {
                                val signInResult = googleAuthUiClient.signInWithIntent(
                                    intent = result.data ?: return@launch
                                )
                                viewModel.onSignInResult(signInResult)
                            }
                        }
                    }
                )

                LaunchedEffect(key1 = state.isSignInSuccessful) {
                    if (state.isSignInSuccessful) {
                        Toast.makeText(
                            context.applicationContext,
                            "Sign in successful",
                            Toast.LENGTH_LONG
                        ).show()

                        Log.d("Screens", "Success Launched Effect launched")
                        navController.navigate(Screens.Home.route)
                    }
                }

                Log.d("Screens", "onCreate: $state")

                SignInScreen(
                    state = state,
                    onSignInClick = {
                        scope.launch {
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
        }

        composable(Screens.Survey.route) {
            SurveyRoute(
                onSurveyComplete = {
                    navController.popBackStack()
                    navController.navigate(Screens.Home.route)

                },
                onNavUp = navController::navigateUp,
            )
        }

        composable(
            route = Screens.Home.route,
            arguments = listOf(navArgument(MEAL_ID_ARGUMENT_KEY,) {
                type = NavType.IntType
            })
        ) {
            HomeScreen(
                navController = navController
            )
        }

        composable(Screens.Recipe.route) {
            MealsScreen(navController = navController)
        }

        composable(Screens.Progress.route) {
            ProgressScreen(navController = navController)
        }

        composable(Screens.Profile.route) {
            ProfileScreen(navController = navController)
        }

        composable(Screens.Settings.route) {
            SettingsScreen(navController = navController)
        }

        composable(Screens.Detail.route) {
            DetailScreen(navController = navController)
        }

        composable(Screens.BreakfastMeals.route) {
            BreakfastMealsScreen(navController = navController)
        }

        composable(Screens.LunchMeals.route) {
            LunchMealsScreen(navController = navController)
        }

        composable(Screens.DinnerMeals.route) {
            DinnerMealsScreen(navController = navController)
        }

        composable(Screens.SnacksMeals.route) {
            SnacksMealsScreen(navController = navController)
        }

        composable(Screens.Search.route) {
            SearchScreen(navController = navController)
        }

        composable(Screens.FoodSelection.route) {
            FoodSelectionScreen(navController = navController)
        }
    }
}
