package com.demircandemir.relaysample.presentation.screens.survey

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.demircandemir.relaysample.R
import com.demircandemir.relaysample.presentation.screens.survey.question.ChoiceItem
import com.demircandemir.relaysample.presentation.screens.survey.question.FillQuestion
import com.demircandemir.relaysample.presentation.screens.survey.question.SingleChoiceQuestion
import com.demircandemir.relaysample.presentation.screens.survey.question.UnitClass

@Composable
fun GoalQuestion(
    selectedAnswer: ChoiceItem?,
    onOptionsSelected: (ChoiceItem) -> Unit,
    modifier: Modifier = Modifier,
) {
    SingleChoiceQuestion(
        titleResourceId = R.string.what_goal_do_you_have_in_mind,
        directionsResourceId = R.string.select_one,
        possibleAnswers = listOf(
            ChoiceItem(R.string.lose_weight),
            ChoiceItem(R.string.maintain_weight),
            ChoiceItem(R.string.gain_weight),
        ),
        selectedAnswer = selectedAnswer,
        onOptionSelected =  onOptionsSelected,
        modifier = modifier,
    )
}

@Composable
fun GenderQuestion(
    selectedAnswer: ChoiceItem?,
    onOptionsSelected: (ChoiceItem) -> Unit,
    modifier: Modifier = Modifier,
) {
    SingleChoiceQuestion(
        titleResourceId = R.string.gender,
        directionsResourceId = R.string.select_one,
        possibleAnswers = listOf(
            ChoiceItem(R.string.female),
            ChoiceItem(R.string.male),
        ),
        selectedAnswer = selectedAnswer,
        onOptionSelected =  onOptionsSelected,
        modifier = modifier,
    )
}

@Composable
fun BirthDayQuestion(
    text: String,
    onTextChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    FillQuestion(
        titleResourceId = R.string.age,
        text = text,
        unit = UnitClass.YEARS,
        onTextChange = onTextChange,
        modifier = modifier
    )
}

@Composable
fun HeightQuestion(
    text: String,
    onTextChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    FillQuestion(
        titleResourceId = R.string.height,
        text = text,
        unit = UnitClass.CM,
        onTextChange = onTextChange,
        modifier = modifier,
    )
}

@Composable
fun WeightQuestion(
    text: String,
    onTextChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    FillQuestion(
        titleResourceId = R.string.weight,
        text = text,
        unit = UnitClass.KG,
        onTextChange = onTextChange,
        modifier = modifier,
    )
}

@Composable
fun ExerciseQuestion(
    text: String,
    onTextChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    FillQuestion(
        titleResourceId = R.string.exercise_day,
        text = text,
        unit = UnitClass.DAY,
        onTextChange = onTextChange,
        modifier = modifier
    )
}

