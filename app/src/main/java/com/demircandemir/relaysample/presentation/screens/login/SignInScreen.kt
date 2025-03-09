package com.demircandemir.relaysample.presentation.screens.login

import android.app.Activity.RESULT_OK
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.demircandemir.relaysample.R
import com.demircandemir.relaysample.navigation.Screens
import com.demircandemir.relaysample.util.Constants.SIGN_IN_SUCCESSFUL

@Composable
fun SignInScreen(
    navController: NavHostController,
    viewModel: SignInViewModel = hiltViewModel()
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val context = LocalContext.current

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartIntentSenderForResult(),
        onResult = { result ->
            if (result.resultCode == RESULT_OK) {
                viewModel.signInWithIntent(
                    intent = result.data ?: return@rememberLauncherForActivityResult
                )
            }
        }
    )

    LaunchedEffect(key1 = uiState.data) {
        if (uiState.data != null) {
            Toast.makeText(
                context.applicationContext,
                SIGN_IN_SUCCESSFUL,
                Toast.LENGTH_LONG
            ).show()
            navController.navigate(Screens.Home.route)
        }
    }

    LaunchedEffect(key1 = uiState.errorMessage) {
        uiState.errorMessage?.let { error ->
            Toast.makeText(context, error, Toast.LENGTH_LONG).show()
        }
    }

    LoginScreenContent(
        onSignInClick = {
            viewModel.signIn()
            val signInIntentSender = uiState.intentSender
            launcher.launch(
                IntentSenderRequest.Builder(
                    signInIntentSender ?: return@LoginScreenContent
                ).build()
            )
        }
    )
}

@Composable
fun LoginScreenContent(onSignInClick: () -> Unit) {
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

            Button(
                onClick = {
                    onSignInClick()
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
        }
    }
}