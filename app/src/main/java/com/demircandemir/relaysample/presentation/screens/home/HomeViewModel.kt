package com.demircandemir.relaysample.presentation.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.demircandemir.relaysample.R
import com.demircandemir.relaysample.domain.model.DailyMealsItem
import com.demircandemir.relaysample.domain.model.UserInfo
import com.demircandemir.relaysample.domain.use_cases.UseCases
import com.demircandemir.relaysample.navigation.Screens
import com.demircandemir.relaysample.util.ApiResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class HomeUiState(
    val meals: List<DailyMealsItem> = emptyList(),
    val totalCalories: Int = 0,
    val totalCarbohydrates: Int = 0,
    val totalProteins: Int = 0,
    val totalFats: Int = 0,
    val userInfo: UserInfo = UserInfo(),
    val isLoading: Boolean = false,
    val error: String? = null
)

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val useCases: UseCases
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState

    init {
        fetchMeals()
        getUserInfo()
    }

    private fun fetchMeals() {
        viewModelScope.launch {
            useCases.getDailyMealsUseCase(1).collect { result ->
                when (result) {
                    is ApiResult.Success -> {
                        result.data?.let { dailyMeals ->
                            val meals = listOf(
                                DailyMealsItem(
                                    name = "Breakfast",
                                    recommendedCalorie = "820 - 1093 kcal",
                                    imageRes = R.drawable.breakfast_icon,
                                    route = Screens.BreakfastMeals.route,
                                    meals = dailyMeals.breakfast.meals
                                ),
                                DailyMealsItem(
                                    name = "Lunch",
                                    recommendedCalorie = "820 - 1093 kcal",
                                    imageRes = R.drawable.lunch_icon,
                                    route = Screens.LunchMeals.route,
                                    meals = dailyMeals.lunch.meals
                                ),
                                DailyMealsItem(
                                    name = "Dinner",
                                    recommendedCalorie = "820 - 1093 kcal",
                                    imageRes = R.drawable.dinner_icon,
                                    route = Screens.DinnerMeals.route,
                                    meals = dailyMeals.dinner.meals
                                ),
                                DailyMealsItem(
                                    name = "Snack",
                                    recommendedCalorie = "820 - 1093 kcal",
                                    imageRes = R.drawable.snacks_icon,
                                    route = Screens.SnacksMeals.route,
                                    meals = dailyMeals.snacks.meals
                                ),
                            )
                            calculateTotals(meals)
                            _uiState.update { currentState ->
                                currentState.copy(
                                    meals = meals,
                                    isLoading = false,
                                    error = null
                                )
                            }
                        }
                    }
                    is ApiResult.Error -> {
                        _uiState.update { currentState ->
                            currentState.copy(
                                isLoading = false,
                                error = result.message
                            )
                        }
                    }
                    is ApiResult.Loading -> {
                        _uiState.update { currentState ->
                            currentState.copy(
                                isLoading = true,
                                error = null
                            )
                        }
                    }
                }
            }
        }
    }

    private fun calculateTotals(meals: List<DailyMealsItem>) {
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

        _uiState.update { currentState ->
            currentState.copy(
                totalCalories = calories,
                totalCarbohydrates = carbohydrates,
                totalProteins = proteins,
                totalFats = fats
            )
        }
    }

    private fun getUserInfo() {
        viewModelScope.launch {
            useCases.getUserInfoFromRemoteUseCase().collect { result ->
                when (result) {
                    is ApiResult.Success -> {
                        result.data?.let { userInfo ->
                            _uiState.update { currentState ->
                                currentState.copy(
                                    userInfo = userInfo,
                                    isLoading = false,
                                    error = null
                                )
                            }
                        }
                    }
                    is ApiResult.Error -> {
                        _uiState.update { currentState ->
                            currentState.copy(
                                userInfo = UserInfo(),
                                isLoading = false,
                                error = result.message
                            )
                        }
                    }
                    is ApiResult.Loading -> {
                        _uiState.update { currentState ->
                            currentState.copy(
                                isLoading = true,
                                error = null
                            )
                        }
                    }
                }
            }
        }
    }
}