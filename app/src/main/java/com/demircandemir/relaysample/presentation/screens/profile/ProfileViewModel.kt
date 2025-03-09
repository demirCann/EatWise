package com.demircandemir.relaysample.presentation.screens.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.demircandemir.relaysample.data.repository.firebase.FirebaseRepository
import com.demircandemir.relaysample.domain.model.FirebaseUserData
import com.demircandemir.relaysample.domain.model.UserInfo
import com.demircandemir.relaysample.domain.use_cases.UseCases
import com.demircandemir.relaysample.util.ApiResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class ProfileUiState(
    val userInfo: UserInfo? = null,
    val firebaseUserData: FirebaseUserData? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val useCases: UseCases,
    private val firebaseRepository: FirebaseRepository
): ViewModel() {

    private val _profileUiState = MutableStateFlow(ProfileUiState())
    val profileUiState: StateFlow<ProfileUiState> = _profileUiState

    init {
        getUserInfo()
        getSignedInUser()
    }

    private fun getUserInfo() {
        viewModelScope.launch(Dispatchers.IO) {
             useCases.getUserInfoFromRemoteUseCase().collect { apiResult ->
                 _profileUiState.update { currentState ->
                     when (apiResult) {
                         is ApiResult.Success -> {
                             currentState.copy(
                                 userInfo = apiResult.data,
                                 isLoading = false,
                                 error = null
                             )
                         }
                         is ApiResult.Error -> {
                             currentState.copy(
                                 isLoading = false,
                                 error = apiResult.message ?: "An error occurred"
                             )
                         }
                         is ApiResult.Loading -> {
                             currentState.copy(isLoading = true)
                         }
                     }
                 }
             }
        }
    }

    private fun getSignedInUser() {
        viewModelScope.launch {
            _profileUiState.update { currentState ->
                when (val firebaseUserData = firebaseRepository.getSignedInUser()) {
                    is ApiResult.Success -> {
                        currentState.copy(
                            firebaseUserData = firebaseUserData.data
                        )
                    }
                    is ApiResult.Error -> {
                        currentState.copy(
                            error = firebaseUserData.message ?: "An error occurred"
                        )
                    }
                    is ApiResult.Loading -> {
                        currentState.copy(isLoading = true)
                    }
                }
            }
        }
    }

    fun signOut() {
        viewModelScope.launch {
            firebaseRepository.signOut()
        }
    }
}