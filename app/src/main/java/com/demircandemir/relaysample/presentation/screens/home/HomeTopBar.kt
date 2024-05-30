package com.demircandemir.relaysample.presentation.screens.home

import android.util.Log
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.demircandemir.relaysample.R
import com.google.firebase.auth.FirebaseAuth

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeTopBar(
    onProfileImageClicked: () -> Unit
) {

    Log.d("HomeScreen", "inside HomeTopBar")

    CenterAlignedTopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.background,
            titleContentColor = MaterialTheme.colorScheme.primary
        ),
        title = {
            Text(
                stringResource(R.string.eatwise),
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


@Composable
fun ProfileImage(onProfileImageClicked: () -> Unit) {
    IconButton(
        onClick = { onProfileImageClicked() }
    ) {

        val imageUrl = FirebaseAuth.getInstance().currentUser?.photoUrl.toString()


        AsyncImage(
            model = imageUrl,
            contentDescription = stringResource(R.string.user_image),
            modifier = Modifier
                .size(24.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )


//        Icon(
//            imageVector = Icons.Filled.AccountCircle,
//            contentDescription = stringResource(R.string.topappbar_profile_image)
//        )
    }
}