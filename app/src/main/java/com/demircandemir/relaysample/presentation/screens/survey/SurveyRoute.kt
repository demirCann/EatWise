package com.demircandemir.relaysample.presentation.screens.survey

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.core.tween
import androidx.compose.animation.togetherWith
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.IntOffset
import androidx.lifecycle.viewmodel.compose.viewModel


private const val CONTENT_ANIMATION_DURATION = 300

@Composable
fun SurveyRoute(
    onSurveyComplete: () -> Unit,
    onNavUp: () -> Unit,
) {
    val viewModel: SurveyViewModel = viewModel()
    val surveyScreenData = viewModel.surveyScreenData ?: return

    BackHandler {
        if (!viewModel.onBackPressed()) {
            onNavUp()
        }
    }


    SurveyQuestionsScreen(
        surveyScreenData = surveyScreenData,
        isNextEnabled = viewModel.isNextEnabled,
        onClosePressed = { onNavUp() },
        onPreviousPressed = { viewModel.onPreviousPressed() },
        onNextPressed = { viewModel.onNextPressed() },
        onDonePressed = { viewModel.onDonePressed(onSurveyComplete) }
    ) {

        AnimatedContent(
            targetState = surveyScreenData,
            transitionSpec = {
                val animationSpec: TweenSpec<IntOffset> = tween(CONTENT_ANIMATION_DURATION)
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
                    GoalQuestion(selectedAnswer = viewModel.goalResponse, onOptionsSelected = viewModel::onGoalResponse)
                }

                SurveyQuestion.GENDER -> {
                    GenderQuestion(selectedAnswer = viewModel.genderResponse, onOptionsSelected = viewModel::onGenderResponse)
                }

                SurveyQuestion.AGE -> {
                    BirthDayQuestion(
                            text = viewModel.ageResponse,
                            onTextChange = viewModel::onAgeResponse
                        )

                }

                SurveyQuestion.WEIGHT -> {
                    WeightQuestion(
                        text = viewModel.weightResponse,
                        onTextChange = viewModel::onWeightResponse
                    )
                }

                SurveyQuestion.HEIGHT -> {
                    HeightQuestion(
                        text = viewModel.heightResponse,
                        onTextChange = viewModel::onHeightResponse
                    )
                }

                SurveyQuestion.EXERCISE -> {
                    ExerciseQuestion(
                        text = viewModel.exerciseResponse,
                        onTextChange = viewModel::onExerciseResponse
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
        // Going forwards in the survey: Set the initial offset to start
        // at the size of the content so it slides in from right to left, and
        // slides out from the left of the screen to -fullWidth
        AnimatedContentTransitionScope.SlideDirection.Left
    } else {
        // Going back to the previous question in the set, we do the same
        // transition as above, but with different offsets - the inverse of
        // above, negative fullWidth to enter, and fullWidth to exit.
        AnimatedContentTransitionScope.SlideDirection.Right
    }
}

