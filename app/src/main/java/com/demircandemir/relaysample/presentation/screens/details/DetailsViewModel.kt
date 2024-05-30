package com.demircandemir.relaysample.presentation.screens.details

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.demircandemir.relaysample.data.remote.EatWiseApi
import com.demircandemir.relaysample.data.repository.Repository
import com.demircandemir.relaysample.domain.model.MealInfo
import com.demircandemir.relaysample.domain.model.MealResponse
import com.demircandemir.relaysample.domain.model.MealsResponse
import com.demircandemir.relaysample.domain.use_cases.UseCases
import com.demircandemir.relaysample.util.Constants.MEAL_ID_ARGUMENT_KEY
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val useCases: UseCases,
    private val repository: Repository,
    private val eatWiseApi: EatWiseApi,
    savedStateHandle: SavedStateHandle
) : ViewModel() {


    private val _selectedMeal: MutableStateFlow<MealResponse?> = MutableStateFlow(null)
    val selectedMeal: StateFlow<MealResponse?> = _selectedMeal


    init {
        viewModelScope.launch(Dispatchers.IO) {
            val mealId = savedStateHandle.get<Int>(MEAL_ID_ARGUMENT_KEY)
            _selectedMeal.value = mealId?.let {
                eatWiseApi.getSelectedMeal(mealId)
            }
//            _selectedMeal.value?.name?.let { Log.d("deneme", it) }
        }
    }

}