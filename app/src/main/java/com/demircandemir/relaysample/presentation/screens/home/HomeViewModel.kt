package com.demircandemir.relaysample.presentation.screens.home

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.demircandemir.relaysample.R
import com.demircandemir.relaysample.data.remote.EatWiseApi
import com.demircandemir.relaysample.domain.model.MealInfo
import com.demircandemir.relaysample.domain.model.UserInfo
import com.demircandemir.relaysample.domain.use_cases.UseCases
import com.demircandemir.relaysample.navigation.Screens
import com.demircandemir.relaysample.util.Constants.MEAL_ID_ARGUMENT_KEY
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val useCases: UseCases,
    private val eatWiseApi: EatWiseApi,
    savedStateHandle: SavedStateHandle
): ViewModel() {



    private val _meals = MutableStateFlow<List<MealAddRow>>(emptyList())
    val meals: StateFlow<List<MealAddRow>> = _meals


    private val _totalCalories = MutableStateFlow(0)
    val totalCalories: StateFlow<Int> = _totalCalories

    private val _totalCarbohydrates = MutableStateFlow(0)
    val totalCarbohydrates: StateFlow<Int> = _totalCarbohydrates

    private val _totalProteins = MutableStateFlow(0)
    val totalProteins: StateFlow<Int> = _totalProteins

    private val _totalFats = MutableStateFlow(0)
    val totalFats: StateFlow<Int> = _totalFats




    init {
        fetchMeals()
        getUserInfo()
    }

    fun fetchMeals() {
        viewModelScope.launch {
            try {
                val breakfastResponse = eatWiseApi.getDietPlan(1, "breakfast")
                val lunchResponse = eatWiseApi.getDietPlan(1, "lunch")
                val dinnerResponse = eatWiseApi.getDietPlan(1, "dinner")
                val snacksResponse = eatWiseApi.getDietPlan(1, "snack")

                _meals.value = listOf(
                    MealAddRow(
                        name = "Breakfast",
                        recommendedCalorie = "820 - 1093 kcal",
                        imageRes = R.drawable.breakfast_icon,
                        route = Screens.BreakfastMeals.route,
                        meals = breakfastResponse.meals
                    ),
                    MealAddRow(
                        name = "Lunch",
                        recommendedCalorie = "820 - 1093 kcal",
                        imageRes = R.drawable.lunch_icon,
                        route = Screens.LunchMeals.route,
                        meals = lunchResponse.meals
                    ),
                    MealAddRow(
                        name = "Dinner",
                        recommendedCalorie = "820 - 1093 kcal",
                        imageRes = R.drawable.dinner_icon,
                        route = Screens.DinnerMeals.route,
                        meals = dinnerResponse.meals
                    ),
                    MealAddRow(
                        name = "Snack",
                        recommendedCalorie = "820 - 1093 kcal",
                        imageRes = R.drawable.snacks_icon,
                        route = Screens.SnacksMeals.route,
                        meals = snacksResponse.meals
                    ),
                )

                calculateTotals(_meals.value)


            } catch (e: Exception) {
                Log.d("HomeViewModel", "fetchMeals: Error: $e")
            }
        }
    }



    private fun calculateTotals(meals: List<MealAddRow>) {
        var calories = 0
        var carbohydrates = 0
        var proteins = 0
        var fats = 0

        meals.forEach { mealRow ->
            mealRow.meals.forEach { meal ->
                calories += meal.calorie.toInt()
                carbohydrates += meal.carbohydrate.toInt()
                proteins += meal.protein.toInt()
                fats += meal.fat.toInt()
            }
        }

        _totalCalories.value = calories
        _totalCarbohydrates.value = carbohydrates
        _totalProteins.value = proteins
        _totalFats.value = fats
    }

    private var _userInfo = MutableStateFlow(
        UserInfo(
        user_id = "",
        name = "",
        goal = "",
        weight = 0,
        height = 0,
        age = 0,
        gender = "",
        BMR = 0.0,
        calculated_intake = 0.0,
        exercise_amount = 0,
        weight_goal = 0,
        time_frame = 0,
        diet_type = ""
    )
    )
    val userInfo: StateFlow<UserInfo> = _userInfo



    fun getUserInfo() {
        viewModelScope.launch(Dispatchers.IO) {
            _userInfo.value = useCases.getUserInfoUseCase()
        }
    }




}