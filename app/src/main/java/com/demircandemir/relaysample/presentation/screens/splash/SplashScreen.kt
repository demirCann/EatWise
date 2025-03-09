package com.demircandemir.relaysample.presentation.screens.splash

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.demircandemir.relaysample.R
import com.demircandemir.relaysample.navigation.Screens
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    navController: NavHostController,
    splashViewModel: SplashViewModel = hiltViewModel()
) {
    val uiState by splashViewModel.splashUiState.collectAsStateWithLifecycle()

    val scale = remember {
        Animatable(0f)
    }

    LaunchedEffect(key1 = Unit) {
        scale.animateTo(
            targetValue = 0.7f,
            animationSpec = tween(
                durationMillis = 800,
                easing = {
                    OvershootInterpolator(4f).getInterpolation(it)
                }
            )
        )
        delay(2000L)

        if (uiState.isUserLoggedIn == true) {
            if (uiState.isSurveyCompleted == true) {
                navController.navigate(Screens.Home.route) {
                    popUpTo(Screens.Splash.route) {
                        inclusive = true
                    }
                }
            } else {
                navController.navigate(Screens.Survey.route) {
                    popUpTo(Screens.Splash.route) {
                        inclusive = true
                    }
                }
            }
        } else {
            navController.navigate(Screens.Login.route) {
                popUpTo(Screens.Splash.route) {
                    inclusive = true
                }
            }
        }
    }

    SplashScreenContent()
}

@Composable
fun SplashScreenContent() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface),
        contentAlignment = Alignment.Center
    ) {
        Image(
            modifier = Modifier.fillMaxSize(0.7f),
            painter = painterResource(id = R.drawable.eatwise_logo),
            contentDescription = stringResource(R.string.eatwise_logo)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SplashScreenPreview() {
    SplashScreenContent()
}