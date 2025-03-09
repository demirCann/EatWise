package com.demircandemir.relaysample.presentation.screens.survey

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.core.tween
import androidx.compose.animation.togetherWith
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.IntOffset
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.demircandemir.relaysample.util.Constants.SURVEY_CONTENT_ANIMATION_DURATION

@Composable
fun SurveyRoute(
    onSurveyComplete: () -> Unit,
    onNavUp: () -> Unit,
    viewModel: SurveyViewModel = hiltViewModel()
) {
    val uiState by viewModel.surveyUiState.collectAsStateWithLifecycle()
    val surveyScreenData = uiState.surveyScreenData ?: return
    val currentUserInfo = uiState.firebaseUserData
    val name = currentUserInfo?.userName ?: ""
    val image = currentUserInfo?.profilePictureUrl ?: ""
    val goal = uiState.goalResponse?.let { stringResource(id = it.stringResourceId) } ?: ""
    val weight = uiState.weightResponse
    val height = uiState.heightResponse
    val age = uiState.ageResponse
    val gender = uiState.genderResponse?.let { stringResource(id = it.stringResourceId) } ?: ""
    val exerciseDayInAWeek = uiState.exerciseResponse
    val weightGoal = uiState.weightGoalResponse
    val timeFrame = uiState.timeFrameResponse
    val dietType = uiState.dietTypeResponse?.let { stringResource(id = it.stringResourceId) } ?: ""

    BackHandler {
        if (!viewModel.onBackPressed()) {
            onNavUp()
        }
    }

    SurveyQuestionsScreen(
        surveyScreenData = surveyScreenData,
        isNextEnabled = uiState.isNextEnabled,
        onClosePressed = { onNavUp() },
        onPreviousPressed = { viewModel.onPreviousPressed() },
        onNextPressed = { viewModel.onNextPressed() },
        onDonePressed = {
            viewModel.onDonePressed(onSurveyComplete)
            viewModel.saveUserInfo(
                id = 1,
                name = name,
                image = image,
                goal = goal,
                weight = weight,
                height = height,
                age = age,
                gender = gender,
                exerciseDayInAWeek = exerciseDayInAWeek,
                weightGoal = weightGoal,
                timeFrame = timeFrame,
                diet_type = dietType
            )
            viewModel.saveSurveyState(true)
        }
    ) {
        AnimatedContent(
            targetState = surveyScreenData,
            transitionSpec = {
                val animationSpec: TweenSpec<IntOffset> = tween(SURVEY_CONTENT_ANIMATION_DURATION)
                val direction = getTransitionDirection(
                    initialIndex = initialState.questionIndex,
                    targetIndex = targetState.questionIndex
                )

                slideIntoContainer(
                    towards = direction,
                    animationSpec = animationSpec
                ) togetherWith slideOutOfContainer(
                    towards = direction,
                    animationSpec = animationSpec
                )
            },
            label = "surveyScreenDataAnimation"
        ) { targetState ->
            when (targetState.surveyQuestion) {
                SurveyQuestion.GOAL -> {
                    GoalQuestion(
                        selectedAnswer = uiState.goalResponse,
                        onOptionsSelected = viewModel::onGoalResponse
                    )
                }
                SurveyQuestion.GENDER -> {
                    GenderQuestion(
                        selectedAnswer = uiState.genderResponse,
                        onOptionsSelected = viewModel::onGenderResponse
                    )
                }
                SurveyQuestion.AGE -> {
                    BirthDayQuestion(
                        text = uiState.ageResponse,
                        onTextChange = viewModel::onAgeResponse
                    )
                }
                SurveyQuestion.WEIGHT -> {
                    WeightQuestion(
                        text = uiState.weightResponse,
                        onTextChange = viewModel::onWeightResponse
                    )
                }
                SurveyQuestion.HEIGHT -> {
                    HeightQuestion(
                        text = uiState.heightResponse,
                        onTextChange = viewModel::onHeightResponse
                    )
                }
                SurveyQuestion.EXERCISE -> {
                    ExerciseQuestion(
                        text = uiState.exerciseResponse,
                        onTextChange = viewModel::onExerciseResponse
                    )
                }
                SurveyQuestion.WEIGHT_GOAL -> {
                    WeightGoalQuestion(
                        text = uiState.weightGoalResponse,
                        onTextChange = viewModel::onWeightGoalResponse
                    )
                }
                SurveyQuestion.TIME_FRAME -> {
                    TimeFrameQuestion(
                        text = uiState.timeFrameResponse,
                        onTextChange = viewModel::onTimeFrameResponse
                    )
                }
                SurveyQuestion.DIET_TYPE -> {
                    DietTypeQuestion(
                        selectedAnswer = uiState.dietTypeResponse,
                        onOptionsSelected = viewModel::onDietTypeResponse
                    )
                }
            }
        }
    }
}

private fun getTransitionDirection(
    initialIndex: Int,
    targetIndex: Int
): AnimatedContentTransitionScope.SlideDirection {
    return if (targetIndex > initialIndex) {
        AnimatedContentTransitionScope.SlideDirection.Left
    } else {
        AnimatedContentTransitionScope.SlideDirection.Right
    }
}