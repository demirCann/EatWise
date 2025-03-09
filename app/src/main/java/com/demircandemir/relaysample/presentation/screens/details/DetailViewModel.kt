package com.demircandemir.relaysample.presentation.screens.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.demircandemir.relaysample.domain.model.MealResponse
import com.demircandemir.relaysample.domain.use_cases.UseCases
import com.demircandemir.relaysample.util.ApiResult
import com.demircandemir.relaysample.util.Constants.MEAL_ID_ARGUMENT_KEY
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class DetailUiState(
    val meal: MealResponse? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val useCases: UseCases,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _uiState = MutableStateFlow(DetailUiState())
    val uiState: StateFlow<DetailUiState> = _uiState.asStateFlow()

    init {
        getSelectedMeal()
    }

    fun retryLoadingMeal() {
        _uiState.update { 
            it.copy(
                error = null,
                isLoading = true,
                meal = null
            )
        }
        getSelectedMeal()
    }

    private fun getSelectedMeal() {
        viewModelScope.launch(Dispatchers.IO) {
            val mealId = savedStateHandle.get<Int>(MEAL_ID_ARGUMENT_KEY)
            mealId?.let {
                useCases.getSelectedMealUseCase(it).collect { apiResult ->
                    _uiState.update { currentState ->
                        when (apiResult) {
                            is ApiResult.Success -> {
                                currentState.copy(
                                    meal = apiResult.data,
                                    isLoading = false,
                                    error = null
                                )
                            }
                            is ApiResult.Error -> {
                                currentState.copy(
                                    isLoading = false,
                                    error = apiResult.message
                                )
                            }
                            is ApiResult.Loading -> {
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
}