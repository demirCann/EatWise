package com.demircandemir.relaysample.presentation.screens.profile

import android.annotation.SuppressLint
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import com.demircandemir.relaysample.presentation.components.ProfileCard

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ProfileContent(
    onBackClicked: () -> Unit,
    onSettingsClicked: () -> Unit
) {
    Scaffold(
        topBar = {
            ProfileTopAppBar(
                onBackClicked = onBackClicked,
                onSettingsClicked = onSettingsClicked
            )
        },
        content = {
            ProfileCard(
                paddingValues = it
            )
        }
    )
}