package com.demircandemir.relaysample.navigation

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.demircandemir.relaysample.presentation.screens.chat.ChatScreen
import com.demircandemir.relaysample.presentation.screens.details.DetailScreen
import com.demircandemir.relaysample.presentation.screens.favorites.FavoriteMealsScreen
import com.demircandemir.relaysample.presentation.screens.home.HomeScreen
import com.demircandemir.relaysample.presentation.screens.login.SignInScreen
import com.demircandemir.relaysample.presentation.screens.profile.ProfileScreen
import com.demircandemir.relaysample.presentation.screens.meals.MealsScreen
import com.demircandemir.relaysample.presentation.screens.meals.dinner_meals.DinnerMealsScreen
import com.demircandemir.relaysample.presentation.screens.meals.lunch_meals.LunchMealsScreen
import com.demircandemir.relaysample.presentation.screens.meals.snacks_meals.SnacksMealsScreen
import com.demircandemir.relaysample.presentation.screens.search.SearchScreen
import com.demircandemir.relaysample.presentation.screens.selection.FoodSelectionScreen
import com.demircandemir.relaysample.presentation.screens.splash.SplashScreen
import com.demircandemir.relaysample.presentation.screens.survey.SurveyRoute
import com.demircandemir.relaysample.util.Constants.MEAL_ID_ARGUMENT_KEY

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SetupNavGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Screens.Splash.route
    ) {
        composable(Screens.Splash.route) {
            SplashScreen(navController = navController)
        }

        composable(Screens.Login.route) {
            SignInScreen(
                navController = navController
            )
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
            arguments = listOf(navArgument(MEAL_ID_ARGUMENT_KEY) {
                type = NavType.IntType
                defaultValue = 0
            })
        ) {
            HomeScreen(
                navController = navController
            )
        }

        composable(Screens.MealList.route) {
            MealsScreen(navController = navController)
        }

        composable(Screens.FavoriteMeals.route) {
            FavoriteMealsScreen()
        }

        composable(Screens.Profile.route) {
            ProfileScreen(navController = navController)
        }

        composable(Screens.Settings.route) {}

        composable(
            Screens.Detail.route,
            arguments = listOf(navArgument(MEAL_ID_ARGUMENT_KEY) {
                type = NavType.IntType
            })
        ) {
            DetailScreen(navController = navController)
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

        composable(Screens.Chat.route) {
            ChatScreen(navController = navController)
        }
    }
}