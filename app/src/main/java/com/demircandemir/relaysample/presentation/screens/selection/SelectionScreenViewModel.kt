package com.demircandemir.relaysample.presentation.screens.selection

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.demircandemir.relaysample.domain.model.MealInfo
import com.demircandemir.relaysample.domain.use_cases.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SelectionScreenViewModel @Inject constructor(
    private val useCases: UseCases,
    savedStateHandle: SavedStateHandle
) : ViewModel() {


    private val _meals: MutableStateFlow<PagingData<MealInfo>> = MutableStateFlow(PagingData.empty())
    val meals = _meals

    val repast = savedStateHandle.get<String>("repast")

    fun getMealsForSelection(repast: String) {
        viewModelScope.launch(Dispatchers.IO) {
            useCases.getMealForSelectionUseCase(repast = repast).collect{
                _meals.value = it
            }
        }

    }
}