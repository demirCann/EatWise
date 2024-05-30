package com.demircandemir.relaysample.presentation.screens.survey

import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.core.tween
import androidx.compose.animation.togetherWith
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.IntOffset
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.demircandemir.relaysample.domain.model.UserInfo
import com.google.firebase.auth.FirebaseAuth


private const val CONTENT_ANIMATION_DURATION = 300

@Composable
fun SurveyRoute(
    onSurveyComplete: () -> Unit,
    onNavUp: () -> Unit,
    viewModel: SurveyViewModel = hiltViewModel()
) {

    val surveyScreenData = viewModel.surveyScreenData ?: return


    var id by remember { mutableStateOf("") }
    id = FirebaseAuth.getInstance().currentUser?.uid ?: ""
    var name by remember { mutableStateOf("") }
    name = FirebaseAuth.getInstance().currentUser?.displayName ?: ""
    var image by remember { mutableStateOf("") }
    image = FirebaseAuth.getInstance().currentUser?.photoUrl.toString()
    var goal by remember { mutableStateOf("") }
    goal = viewModel.goalResponse?.let { it1 -> stringResource(id = it1.stringResourceId) }
        .toString()
    Log.d("SurveyRoute", "Goal: $goal")
    var weight by remember { mutableStateOf("") }
    weight = viewModel.weightResponse
    var height by remember { mutableStateOf("") }
    height = viewModel.heightResponse
    var age by remember { mutableStateOf("") }
    age = viewModel.ageResponse
    var gender by remember { mutableStateOf("") }
    gender = viewModel.genderResponse?.stringResourceId?.let { it1 -> stringResource(id = it1) }
        .toString()
    var bmr by remember { mutableStateOf("") }
    bmr = 13459981237213.toString()
    var exerciseDayInAWeek by remember { mutableStateOf("") }
    exerciseDayInAWeek = viewModel.exerciseResponse

    var weightGoal by remember { mutableStateOf("") }
    weightGoal = viewModel.weightGoalResponse
    var timeFrame by remember { mutableStateOf("") }
    timeFrame = viewModel.timeFrameResponse
    var dietType by remember { mutableStateOf("") }
    dietType = viewModel.dietTypeResponse?.let { it1 -> stringResource(id = it1.stringResourceId) }
        .toString()


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
        }
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
//                    gender = viewModel.genderResponse?.stringResourceId?.let { it1 -> stringResource(id = it1) }
//                        .toString()
                }

                SurveyQuestion.AGE -> {
                    BirthDayQuestion(
                            text = viewModel.ageResponse,
                            onTextChange = viewModel::onAgeResponse
                        )
//                    age = viewModel.ageResponse
//                    Log.d("SurveyRoute","Age: ${viewModel.ageResponse}")
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

                SurveyQuestion.WEIGHT_GOAL -> {
                    WeightGoalQuestion(
                        text = viewModel.weightGoalResponse,
                        onTextChange = viewModel::onWeightGoalResponse
                    )
                }

                SurveyQuestion.TIME_FRAME -> {
                    TimeFrameQuestion(
                        text = viewModel.timeFrameResponse,
                        onTextChange = viewModel::onTimeFrameResponse
                    )
                }

                SurveyQuestion.DIET_TYPE -> {
                    DietTypeQuestion(
                        selectedAnswer = viewModel.dietTypeResponse,
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

