package com.demircandemir.relaysample.presentation.screens.profile

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.demircandemir.relaysample.navigation.Screens
import com.demircandemir.relaysample.presentation.components.ProfileCard

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ProfileContent(
    profileViewModel: ProfileViewModel = hiltViewModel(),
    onBackClicked: () -> Unit,
    onSettingsClicked: () -> Unit,
    navController: NavHostController
) {


    val userInfo by profileViewModel.userInfo.collectAsState()


    LaunchedEffect(true) {
        profileViewModel.getUserInfo()
    }


    Scaffold(
        topBar = {
            ProfileTopAppBar(
                onBackClicked = onBackClicked,
                onSettingsClicked = onSettingsClicked
            )
        },
        content = {
            ProfileCard(
                userInfo = userInfo,
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