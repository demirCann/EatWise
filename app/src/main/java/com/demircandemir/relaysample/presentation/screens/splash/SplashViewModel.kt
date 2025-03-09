package com.demircandemir.relaysample.presentation.screens.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.demircandemir.relaysample.data.repository.firebase.FirebaseRepository
import com.demircandemir.relaysample.domain.use_cases.UseCases
import com.demircandemir.relaysample.util.ApiResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import javax.inject.Inject

data class SplashUiState(
    val isSurveyCompleted: Boolean? = null,
    val isUserLoggedIn: Boolean? = null,
    val error: String? = null
)

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val firebaseRepository: FirebaseRepository,
    private val useCases: UseCases
) : ViewModel() {

    private val _splashUiState = MutableStateFlow(SplashUiState())
    val splashUiState: StateFlow<SplashUiState> = _splashUiState

    init {
        checkUserStatus()
    }

    private fun checkUserStatus() {
        viewModelScope.launch(Dispatchers.IO) {
            when (val isUserLoggedIn = firebaseRepository.getSignedInUser()) {
                is ApiResult.Success -> {
                    val isSurveyCompleted = useCases.readSurveyUseCase().firstOrNull()
                    _splashUiState.value =
                        SplashUiState(isSurveyCompleted = isSurveyCompleted, isUserLoggedIn = true)
                }

                is ApiResult.Error -> {
                    _splashUiState.value = SplashUiState(error = isUserLoggedIn.message)
                }
                ApiResult.Loading -> {}
            }
        }
    }
}