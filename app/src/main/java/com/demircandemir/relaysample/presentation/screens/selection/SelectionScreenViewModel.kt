package com.demircandemir.relaysample.presentation.screens.selection

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.demircandemir.relaysample.domain.model.MealInfo
import com.demircandemir.relaysample.domain.model.MealsRequest
import com.demircandemir.relaysample.domain.use_cases.UseCases
import com.demircandemir.relaysample.util.ApiResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class SelectionScreenUiState(
    val meals: Flow<PagingData<MealInfo>> = emptyFlow(),
    val selectedMeals: List<Int> = emptyList(),
    val repast: String? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)

@HiltViewModel
class SelectionScreenViewModel @Inject constructor(
    private val useCases: UseCases,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _selectionUiState = MutableStateFlow(SelectionScreenUiState())
    val selectionUiState = _selectionUiState.asStateFlow()

    init {
        savedStateHandle.get<String>("repast")?.let { repast ->
            _selectionUiState.update { currentState ->
                currentState.copy(repast = repast)
            }
            getMealsForSelection(repast)
            getCurrentDietPlan(repast)
        }
    }

    fun addMeal(mealId: Int) {
        _selectionUiState.update { currentState ->
            if (!currentState.selectedMeals.contains(mealId)) {
                currentState.copy(
                    selectedMeals = currentState.selectedMeals + mealId
                )
            } else {
                currentState
            }
        }
    }

    fun removeMeal(mealId: Int) {
        _selectionUiState.update { currentState ->
            currentState.copy(
                selectedMeals = currentState.selectedMeals - mealId
            )
        }
    }

    private fun getCurrentDietPlan(repast: String) {
        viewModelScope.launch(Dispatchers.IO) {
            useCases.getDietPlanUseCase(userId = 1, repast = repast).collect { apiResult ->
                when (apiResult) {
                    is ApiResult.Success -> {
                        apiResult.data?.let { meals ->
                            _selectionUiState.update { currentState ->
                                currentState.copy(
                                    selectedMeals = meals.meals.map { it.id }
                                )
                            }
                        }
                    }
                    is ApiResult.Error -> {
                        _selectionUiState.update { currentState ->
                            currentState.copy(
                                error = apiResult.message,
                                isLoading = false
                            )
                        }
                    }
                    ApiResult.Loading -> {
                        _selectionUiState.update { currentState ->
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

    private fun getMealsForSelection(repast: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _selectionUiState.update { currentState ->
                currentState.copy(
                    isLoading = true,
                    meals = useCases.getMealForSelectionUseCase(repast = repast)
                )
            }
        }
    }

    fun putDietPlan(userId: Int, repast: String, meals: MealsRequest) {
        viewModelScope.launch(Dispatchers.IO) {
            useCases.updateDietPlanUseCase(userId = userId, repast = repast, meals = meals)
        }
    }
}