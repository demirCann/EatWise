package com.demircandemir.relaysample.presentation.screens.login

import android.content.Intent
import android.content.IntentSender
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.demircandemir.relaysample.data.repository.firebase.FirebaseRepository
import com.demircandemir.relaysample.domain.model.FirebaseUserData
import com.demircandemir.relaysample.util.ApiResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class LoginUiState(
    val data: FirebaseUserData? = null,
    val intentSender: IntentSender? = null,
    val errorMessage: String? = null
)

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val firebaseRepository: FirebaseRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState = _uiState.asStateFlow()

    fun signInWithIntent(intent: Intent) {
        viewModelScope.launch {
            when (val signInResult = firebaseRepository.signInWithIntent(intent)) {
                is ApiResult.Success -> {
                    _uiState.update {
                        it.copy(
                            data = signInResult.data
                        )
                    }
                }

                is ApiResult.Error -> {
                    _uiState.update {
                        it.copy(
                            errorMessage = signInResult.message
                        )
                    }
                }

                ApiResult.Loading -> {}
            }
        }
    }

    fun signIn() {
        viewModelScope.launch {
            when (val signInResult = firebaseRepository.signIn()) {
                is ApiResult.Success -> {
                    _uiState.update {
                        it.copy(
                            intentSender = signInResult.data
                        )
                    }
                }

                is ApiResult.Error -> {
                    _uiState.update {
                        it.copy(
                            errorMessage = signInResult.message
                        )
                    }
                }

                ApiResult.Loading -> {}
            }
        }
    }
}