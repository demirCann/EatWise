package com.demircandemir.relaysample.presentation.screens.profile

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.demircandemir.relaysample.domain.model.FirebaseUserData
import com.demircandemir.relaysample.domain.model.UserInfo
import com.demircandemir.relaysample.navigation.Screens
import com.demircandemir.relaysample.presentation.components.ProfileCard

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ProfileScreen(
    navController: NavHostController,
    profileViewModel: ProfileViewModel = hiltViewModel(),
) {
    val uiState by profileViewModel.profileUiState.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            ProfileTopAppBar(
                onBackClicked = {
                    navController.navigate(Screens.Home.route)
                },
                onSettingsClicked = {
                    navController.navigate(Screens.Settings.route)
                }
            )
        },
        content = {
            ProfileCard(
                userInfo = uiState.userInfo ?: UserInfo(), // TODO: 10/02/2025  handle null case
                firebaseUserData = uiState.firebaseUserData ?: FirebaseUserData(),
                paddingValues = it
            )
        },
        bottomBar = {
            Button(
                onClick = {
                    profileViewModel.signOut()
                    navController.navigate(Screens.Login.route) {
                        popUpTo(0) { inclusive = true }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(text = "Sign Out")
            }
        }
    )
}




