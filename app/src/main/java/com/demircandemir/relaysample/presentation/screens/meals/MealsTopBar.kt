package com.demircandemir.relaysample.presentation.screens.meals

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.demircandemir.relaysample.R
import com.demircandemir.relaysample.presentation.screens.profile.SettingsButton

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MealsTopBar(
    topBarName: String,
    onSearchClicked: () -> Unit,
    onBackClicked: () -> Unit
) {
    TopAppBar(
        title = { Text(text = topBarName) },
        navigationIcon = {
            if (topBarName == "Meals") {} else {
                BackButton{
                    onBackClicked()
                }
            }
        },
        actions = {
            SearchButton {
                onSearchClicked()
            }
        }
    )
}

@Composable
fun BackButton(
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
fun SearchButton(
    onSearchClicked: () -> Unit
) {
    IconButton(onClick = { onSearchClicked() }) {
        Icon(
            imageVector = Icons.Filled.Search,
            contentDescription = stringResource(R.string.search_button)
        )
    }
}