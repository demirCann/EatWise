package com.demircandemir.relaysample.presentation.screens.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.demircandemir.relaysample.domain.model.UserInfo
import com.demircandemir.relaysample.domain.use_cases.UseCases
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val useCases: UseCases
): ViewModel() {

    val auth = FirebaseAuth.getInstance()

    private var _userInfo = MutableStateFlow(UserInfo(
        user_id = "",
        name = "",
        goal = "",
        weight = 0,
        height = 0,
        age = 0,
        gender = "",
        BMR = 0.0,
        calculated_intake = 0.0,
        exercise_amount = 0,
        weight_goal = 0,
        time_frame = 0,
        diet_type = ""
    ))
    val userInfo: StateFlow<UserInfo> = _userInfo



    fun getUserInfo() {
        viewModelScope.launch(Dispatchers.IO) {
             _userInfo.value = useCases.getUserInfoUseCase()
        }
    }

    fun signOut() {
        auth.signOut()
    }

}