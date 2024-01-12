package com.demircandemir.relaysample.presentation.screens.survey.question

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.demircandemir.relaysample.R
import com.demircandemir.relaysample.presentation.screens.survey.QuestionWrapper

@Composable
fun SingleChoiceQuestion(
    @StringRes titleResourceId: Int,
    @StringRes directionsResourceId: Int,
    possibleAnswers: List<ChoiceItem>,
    selectedAnswer: ChoiceItem?,
    onOptionSelected: (ChoiceItem) -> Unit,
    modifier: Modifier = Modifier,
) {

    QuestionWrapper(
        titleResourceId = titleResourceId,
        directionsResourceId = directionsResourceId,
        modifier = modifier.selectableGroup()
    ) {
        possibleAnswers.forEach {
            val selected = it == selectedAnswer
            QuestionRow(
                modifier = Modifier.padding(vertical = 8.dp),
                text = stringResource(id = it.stringResourceId),
                selected = selected,
                onOptionSelected = { onOptionSelected(it) }
            )

        }
    }

}


@Composable
fun QuestionRow(
    modifier: Modifier = Modifier,
    text: String,
    selected: Boolean,
    onOptionSelected: () -> Unit,
) {
    val color = if (selected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface.copy(alpha = 0.2f)
    val textColor = if (selected) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurface

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(120.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(color)
            .selectable(
                selected = selected,
                onClick = onOptionSelected,
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            color = textColor,
            style = MaterialTheme.typography.titleLarge
        )
    }

}


@Preview(showBackground = true)
@Composable
fun SingleChoiceQuestionPreview() {
    val possibleAnswers = listOf(
        ChoiceItem(R.string.lose_weight),
        ChoiceItem(R.string.maintain_weight),
        ChoiceItem(R.string.gain_weight),
    )
    var selectedAnswer by remember { mutableStateOf<ChoiceItem?>(null) }

    SingleChoiceQuestion(
        titleResourceId = R.string.what_goal_do_you_have_in_mind,
        directionsResourceId = R.string.select_one,
        possibleAnswers = possibleAnswers,
        selectedAnswer = selectedAnswer,
        onOptionSelected = { selectedAnswer = it },
    )
}



data class ChoiceItem(
    @StringRes val stringResourceId: Int
)