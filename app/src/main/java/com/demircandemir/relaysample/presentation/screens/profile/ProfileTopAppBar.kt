package com.demircandemir.relaysample.presentation.screens.profile

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.demircandemir.relaysample.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileTopAppBar(
    onBackClicked: () -> Unit,
    onSettingsClicked: () -> Unit
) {
    TopAppBar(
        title = { Text(text = "Profile") },
        navigationIcon = {
            NavigationIconFun {
                onBackClicked()
            }
        },
        actions = {
            SettingsButton {
                onSettingsClicked()
            }
        }
    )
}


@Composable
fun NavigationIconFun(
    onBackClicked: () -> Unit
) {
    IconButton(onClick = { onBackClicked() }) {
        Icon(
            imageVector = Icons.Filled.ArrowBack,
            contentDescription = stringResource(R.string.backarrowbutton)
        )
    }
}

@Composable
fun SettingsButton(
    onSettingsClicked: () -> Unit
) {
    IconButton(onClick = { onSettingsClicked() }) {
        Icon(
            imageVector = Icons.Filled.Settings,
            contentDescription = stringResource(R.string.settingsbutton)
        )
    }
}