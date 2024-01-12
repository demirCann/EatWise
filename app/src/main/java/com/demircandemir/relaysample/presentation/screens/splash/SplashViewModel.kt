package com.demircandemir.relaysample.presentation.screens.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.demircandemir.relaysample.domain.use_cases.UseCases
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

class SplashViewModel @Inject constructor(
    private val useCases: UseCases
) : ViewModel() {

    private val _isSurveyCompleted = MutableStateFlow(false)
    val onSurveyCompleted: StateFlow<Boolean> = _isSurveyCompleted

    init {
        viewModelScope.launch(Dispatchers.IO) {
            _isSurveyCompleted.value = useCases.readSurveyUseCase().stateIn(viewModelScope).value
        }
    }

}