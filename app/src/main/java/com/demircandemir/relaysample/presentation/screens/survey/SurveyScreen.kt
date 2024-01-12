package com.demircandemir.relaysample.presentation.screens.survey

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SurveyScreen() {
    val pageCount = 5
    val pagerState = rememberPagerState(pageCount = { pageCount })

    Box(modifier = Modifier) {




        HorizontalPager(
            modifier = Modifier.fillMaxSize(),
            state = pagerState
        ) {

        }

        LineIndicator(
            modifier = Modifier
                .height(24.dp)
                .padding(start = 4.dp)
                .fillMaxWidth()
                .align(Alignment.TopCenter),
            pageCount = pageCount,
            pagerState = pagerState
        )
    }
}

@Preview
@Composable
fun PreviewSurveyScreen() {
    SurveyScreen()
}