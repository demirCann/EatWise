package com.demircandemir.relaysample.presentation.screens.login

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerIcon.Companion.Text
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.demircandemir.relaysample.R


@Composable
fun SignInScreen(
    state: LogInState,
    onSignInClick: () -> Unit,
) {

    Log.d("Screens", "SignInScreen: Executed")


    val context = LocalContext.current
    LaunchedEffect(key1 = state.signInErrorMessage) {
        Log.d("Screens", "signInErrorMessage: ${state.signInErrorMessage}")
        state.signInErrorMessage?.let { error ->
            Toast.makeText(context, error, Toast.LENGTH_LONG).show()
        }
    }

    LoginScreenContent {
        onSignInClick()
    }
}



@Composable
fun LoginScreenContent(onLogInClick: () -> Unit) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center,
    ) {

        Column {
            Image(
                modifier = Modifier.size(400.dp),
                painter = painterResource(id = R.drawable.login_image),
                contentDescription = stringResource(R.string.login_image)
            )

            Spacer(modifier = Modifier.padding(48.dp))

            androidx.compose.material3.Button(
                onClick = {
                onLogInClick()
            },
                modifier = Modifier
                    .fillMaxWidth(),
                colors = ButtonDefaults.buttonColors()
            ) {
                Icon(
                    modifier = Modifier
                        .size(16.dp),
                    painter = painterResource(id = R.drawable.google_icon),
                    contentDescription = "Google Icon",
                    tint = Color.White
                )
                Spacer(modifier = Modifier.padding(5.dp))

                Text(text = "Sign in with Google")
            }

            //com.demircandemir.relaysample.presentation.components.SGnWithGoogle(onClick = onLogInClick)
        }
    }
}




