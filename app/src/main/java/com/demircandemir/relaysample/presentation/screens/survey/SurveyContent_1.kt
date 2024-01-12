@file:Suppress("PreviewAnnotationInFunctionWithParameters")

package com.demircandemir.relaysample.presentation.screens.survey

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.demircandemir.relaysample.R

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SurveyContent_1(
    pagerState: PagerState,
    pageCount: Int,
    selected: Boolean,
    onChoiceClicked: () -> Unit,
    //onBackClicked: () -> Unit,
) {


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { /*onBackClicked()*/ }) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = stringResource(R.string.backarrowbutton)
                )
            }

            LineIndicator(
                modifier = Modifier
                    .height(10.dp)
                    .padding(start = 20.dp),
                pageCount = pageCount,
                pagerState = pagerState
            )

        }

        Spacer(modifier = Modifier.height(32.dp))

        Text(
            "What goal do you have in mind?",
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.headlineLarge
        )

        Spacer(modifier = Modifier.height(32.dp))

        SurveyChoice(
            text = "Lose weight",
            selected,
            onClicked = { onChoiceClicked() }
        )
        Spacer(modifier = Modifier.height(16.dp))

        SurveyChoice(
            text = "Maintain weight" ,
            selected,
            onClicked = { onChoiceClicked() }
        )
        Spacer(modifier = Modifier.height(16.dp))

        SurveyChoice(
            text = "Gain weight",
            selected,
            onClicked = { onChoiceClicked() }
        )




    }
}

@Composable
fun SurveyChoice(
    text: String,
    selected: Boolean,
    onClicked: () -> Unit
) {

    val color = if (selected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface.copy(alpha = 0.2f)
    val textColor = if (selected) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurface

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(color)
            .clickable { onClicked() },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            color = textColor,
            style = MaterialTheme.typography.titleLarge
        )
    }
}

@Composable
fun NextButton() {

}


@OptIn(ExperimentalFoundationApi::class)
@Preview(showBackground = true)
@Composable
fun PreviewSurveyContent_1() {
    SurveyContent_1(
        pagerState = rememberPagerState(pageCount = { 5 }),
        pageCount = 5,
        onChoiceClicked = {},
        selected = true
    )
}