package com.demircandemir.relaysample.presentation.screens.home

import android.util.Log
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import com.demircandemir.relaysample.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeTopBar(
    onProfileImageClicked: () -> Unit
) {

    Log.d("HomeScreen", "inside HomeTopBar")

    CenterAlignedTopAppBar(
        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.background,
            titleContentColor = MaterialTheme.colorScheme.primary
        ),
        title = {
            Text(
                "EatWise",
                color = Color.Black,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        },
        actions = {
            ProfileImage {
                onProfileImageClicked()
            }
        }


    )
}


/*@Composable
fun NavigationDrawer(onNavigationDrawerClicked: () -> Unit) {
    IconButton(
        onClick = { onNavigationDrawerClicked() }
    ) {
        Icon(
            imageVector =Icons.Filled.Menu,
            contentDescription = stringResource(R.string.topappbar_navigation_drawer),
        )
    }
}*/


@Composable
fun ProfileImage(onProfileImageClicked: () -> Unit) {
    IconButton(
        onClick = { onProfileImageClicked() }
    ) {
        Icon(
            imageVector = Icons.Filled.AccountCircle,
            contentDescription = stringResource(R.string.topappbar_profile_image)
        )
    }
}