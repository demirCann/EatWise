package com.demircandemir.relaysample.presentation.screens.home

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.demircandemir.relaysample.domain.model.MealInfo
import com.demircandemir.relaysample.domain.use_cases.UseCases
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
    savedStateHandle: SavedStateHandle
): ViewModel() {




    private val _selectedMeal: MutableStateFlow<MealInfo?> = MutableStateFlow(null)
    val selectedMeal: StateFlow<MealInfo?> = _selectedMeal

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val mealId = savedStateHandle.get<Int>(MEAL_ID_ARGUMENT_KEY)
            _selectedMeal.value = mealId?.let {
                useCases.getSelectedMealUseCase(id = mealId)
            }
            _selectedMeal.value?.name?.let { Log.d("home_argument", it) }
        }
    }
}