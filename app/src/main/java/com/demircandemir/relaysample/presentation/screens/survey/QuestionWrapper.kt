package com.demircandemir.relaysample.presentation.screens.survey

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.demircandemir.relaysample.ui.theme.slightlyDeemphasizedAlpha

@Composable
fun QuestionWrapper(
   @StringRes titleResourceId: Int,
   modifier: Modifier = Modifier,
   @StringRes directionsResourceId: Int? = null,
   content: @Composable () -> Unit,
) {
    Column(
        modifier = modifier
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Spacer(modifier = Modifier.height(32.dp))
        QuestionTitle(titleResourceId)
        directionsResourceId?.let {
            Spacer(modifier = Modifier.height(18.dp))
            QuestionDirections(it)
        }
        Spacer(modifier = Modifier.height(18.dp))

        content()

    }
}


@Composable
private fun QuestionTitle(
    @StringRes title: Int,
    modifier: Modifier = Modifier,
) {
    Text(
        text = stringResource(id = title),
        style = MaterialTheme.typography.headlineLarge,
        textAlign = TextAlign.Center,
        color = MaterialTheme.colorScheme.onSurface.copy(alpha = slightlyDeemphasizedAlpha),
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 24.dp, horizontal = 16.dp)
    )
}


@Composable
private fun QuestionDirections(
    @StringRes directionsResourceId: Int,
    modifier: Modifier = Modifier,
) {
    Text(
        text = stringResource(id = directionsResourceId),
        color = MaterialTheme.colorScheme.onSurface
            .copy(alpha = slightlyDeemphasizedAlpha),
        style = MaterialTheme.typography.bodySmall,
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp)
    )
}














