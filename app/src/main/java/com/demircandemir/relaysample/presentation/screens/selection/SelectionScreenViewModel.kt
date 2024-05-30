package com.demircandemir.relaysample.presentation.screens.selection

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.demircandemir.relaysample.data.remote.EatWiseApi
import com.demircandemir.relaysample.domain.model.MealInfo
import com.demircandemir.relaysample.domain.model.MealsRequest
import com.demircandemir.relaysample.domain.use_cases.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SelectionScreenViewModel @Inject constructor(
    private val useCases: UseCases,
    private val eatWiseApi: EatWiseApi,
    savedStateHandle: SavedStateHandle
) : ViewModel() {


    private val _meals: MutableStateFlow<PagingData<MealInfo>> = MutableStateFlow(PagingData.empty())
    val meals = _meals.asStateFlow()

    val repast = savedStateHandle.get<String>("repast")


    private val _selectedMeals = MutableStateFlow<List<Int>>(emptyList())
    val selectedMeals: StateFlow<List<Int>> = _selectedMeals.asStateFlow()


    init {
        repast?.let {
            getMealsForSelection(it)
            getCurrentDietPlan(repast =  it)
        }
    }


    fun addMeal(mealId: Int) {
        if (!selectedMeals.value.contains(mealId)) {
            _selectedMeals.value += mealId
        }
    }

    fun removeMeal(mealId: Int) {
        _selectedMeals.value -= mealId
    }


    private fun getCurrentDietPlan(repast: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = eatWiseApi.getDietPlan(repast = repast)
            _selectedMeals.value = response.meals.map { it.id }
        }
    }


    fun getMealsForSelection(repast: String) {
        viewModelScope.launch(Dispatchers.IO) {
            useCases.getMealForSelectionUseCase(repast = repast).collect{
                _meals.value = it
            }
        }

    }

    fun putDietPlan(userId: Int, repast: String, meals: MealsRequest) {
        viewModelScope.launch(Dispatchers.IO) {
            eatWiseApi.updateDietPlan(userId = userId, repast = repast, mealsRequest = meals)
        }
    }


//    fun postDietPlan(userId: Int, repast: String, meals: String) {
//        viewModelScope.launch(Dispatchers.IO) {
//            useCases.postDietPlanUseCase(userId = 1, repast = repast, meals = meals)
//        }
//    }

}