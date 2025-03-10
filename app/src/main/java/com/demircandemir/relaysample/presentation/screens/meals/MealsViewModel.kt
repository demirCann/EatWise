package com.demircandemir.relaysample.presentation.screens.meals

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.demircandemir.relaysample.domain.model.MealInfo
import com.demircandemir.relaysample.domain.use_cases.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MealsViewModel @Inject constructor(
    private val useCases: UseCases
) : ViewModel() {

    private val _allMeals = MutableStateFlow<PagingData<MealInfo>>(PagingData.empty())
    val allMeals = _allMeals.asStateFlow()

    init {
        getAllMeals()
    }

    private fun getAllMeals() {
        viewModelScope.launch(Dispatchers.IO) {
            useCases.getAllMealsUseCase().cachedIn(viewModelScope).collect {
                _allMeals.value = it
            }
        }
    }
}