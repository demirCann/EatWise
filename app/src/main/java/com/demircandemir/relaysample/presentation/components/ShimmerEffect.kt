package com.demircandemir.relaysample.presentation.components

import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.demircandemir.relaysample.ui.theme.ShimmerDarkGray
import com.demircandemir.relaysample.ui.theme.ShimmerLightGray
import com.demircandemir.relaysample.ui.theme.ShimmerMediumGray

@Composable
fun ShimmerEffect() {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(140.dp),
        modifier = Modifier
            .fillMaxHeight(),
        contentPadding = PaddingValues(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(4) {
            AnimatedShimmerItem()
        }
    }
}


@Composable
fun AnimatedShimmerItem() {
    val transition = rememberInfiniteTransition(label = "")
    val alphaAnim by transition.animateFloat(
        initialValue = 1f,
        targetValue = 0f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 500,
                easing = FastOutLinearInEasing
            ),
            repeatMode = RepeatMode.Reverse
        ), label = ""
    )
    ShimmerItem(alpha = alphaAnim)
}


@Composable
fun ShimmerItem(alpha: Float) {
    Surface(
        modifier = Modifier
            .size(140.dp, 280.dp),
        color = if (isSystemInDarkTheme())
            Color.Black else ShimmerLightGray,
        shape = RoundedCornerShape(20.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {

            Surface(
                modifier = Modifier
                    .size(140.dp, 150.dp),
                color = if (isSystemInDarkTheme())
                    ShimmerDarkGray else ShimmerMediumGray,
                shape = RoundedCornerShape(10.dp)
            ){}

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp, end = 8.dp, top = 8.dp)
            ) {
                Surface(
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .size(100.dp, 30.dp),
                    color = if (isSystemInDarkTheme())
                        ShimmerDarkGray else ShimmerMediumGray,
                    shape = RoundedCornerShape(10.dp)
                ) {}

                Spacer(modifier = Modifier.padding(16.dp))

                Surface(
                    modifier = Modifier
                        .padding(top = 8.dp)
                        .size(50.dp, 15.dp),
                    color = if (isSystemInDarkTheme())
                        ShimmerDarkGray else ShimmerMediumGray,
                    shape = RoundedCornerShape(10.dp)
                ) {}


            }

        }


    }
}


@Composable
@Preview
fun AnimatedShimmerItemPreview() {
    AnimatedShimmerItem()
}