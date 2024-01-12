/*package com.demircandemir.relaysample.navigation

import android.app.Activity.RESULT_OK
import android.content.Context
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.demircandemir.relaysample.presentation.screens.login.GoogleAuthUiClient
import com.demircandemir.relaysample.presentation.screens.login.SignInScreen
import com.demircandemir.relaysample.presentation.screens.login.SignInViewModel
import com.google.android.gms.auth.api.identity.Identity
import kotlinx.coroutines.launch

fun NavGraphBuilder.loginComposable(
    context: Context,
    navController: NavHostController,
    lifecyclescope: LifecycleCoroutineScope
) {



    val googleAuthUiClient by lazy {
        GoogleAuthUiClient(
            context = context.applicationContext,
            oneTapClient = Identity.getSignInClient(context.applicationContext)
        )
    }

    composable(
        route = Screens.Login.route
    ) {

        val viewModel = viewModel<SignInViewModel>()
        val state by viewModel.state.collectAsState()

        LaunchedEffect(key1 = Unit) {
            if(googleAuthUiClient.getSignedInUser() != null) {
                navController.navigate("profile")
            }
        }

        val launcher = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.StartIntentSenderForResult(),
            onResult = { result ->
                if(result.resultCode == RESULT_OK) {
                    lifecyclescope.launch {
                        val signInResult = googleAuthUiClient.signInWithIntent(
                            intent = result.data ?: return@launch
                        )
                        viewModel.onSignInResult(signInResult)
                    }
                }
            }
        )

        LaunchedEffect(key1 = state.isSignInSuccessful) {
            if(state.isSignInSuccessful) {
                Toast.makeText(
                    context.applicationContext,
                    "Sign in successful",
                    Toast.LENGTH_LONG
                ).show()

                navController.navigate("profile")
                viewModel.resetState()
            }
        }

        SignInScreen(
            state = state,
            onSignInClick = {
                lifecyclescope.launch {
                    val signInIntentSender = googleAuthUiClient.signIn()
                    launcher.launch(
                        IntentSenderRequest.Builder(
                            signInIntentSender ?: return@launch
                        ).build()
                    )
                }
            }
        )

    }

}
*/