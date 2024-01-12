package com.demircandemir.relaysample.presentation.screens.login

data class LogInState(
    val isSignInSuccessful: Boolean = false,
    val signInErrorMessage: String? = null
)
