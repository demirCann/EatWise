package com.demircandemir.relaysample.navigation

import com.demircandemir.relaysample.domain.model.MealInfo

sealed class Screens(val route: String) {

    data object Splash : Screens("splash_screen")

    data object Login : Screens("login_screen")

    data object Survey: Screens("survey_screen")

    data object Home : Screens("home_screen") {
        fun passMealId(mealId: Int): String {
            return "home_screen/$mealId"
        }
    }

    data object Recipe : Screens("recipe_screen")

    data object Progress : Screens("progress_screen")

    data object Profile : Screens("profile_screen")

    data object Settings : Screens("settings_screen")

    data object Detail : Screens("detail_screen/{mealId}") {
        fun passMealInfo(mealId: Int): String {
            return "detail_screen/$mealId"
        }
    }

    data object BreakfastMeals : Screens("breakfast_meals_screen/{repast}") {
        fun passRepast(repast: String): String {
            return "breakfast_meals_screen/$repast"
        }
    }

    data object LunchMeals : Screens("lunch_meals_screen")

    data object DinnerMeals : Screens("dinner_meals_screen")

    data object SnacksMeals : Screens("snacks_meals_screen")

    data object Search : Screens("search_screen")

    data object FoodSelection : Screens("food_selection_screen/{repast}") {
        fun passRepast(repast: String): String {
            return "food_selection_screen/$repast"
        }
    }



}




