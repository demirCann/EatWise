package com.demircandemir.relaysample.presentation.screens.survey

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.demircandemir.relaysample.data.repository.firebase.FirebaseRepository
import com.demircandemir.relaysample.domain.model.FirebaseUserData
import com.demircandemir.relaysample.domain.use_cases.UseCases
import com.demircandemir.relaysample.presentation.screens.survey.question.ChoiceItem
import com.demircandemir.relaysample.util.ApiResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class SurveyUiState(
    val isSurveyDone: Boolean? = null,
    val goalResponse: ChoiceItem? = null,
    val genderResponse: ChoiceItem? = null,
    val ageResponse: String = "",
    val weightResponse: String = "",
    val heightResponse: String = "",
    val exerciseResponse: String = "",
    val weightGoalResponse: String = "",
    val timeFrameResponse: String = "",
    val dietTypeResponse: ChoiceItem? = null,
    val questionIndex: Int = 0,
    val isNextEnabled: Boolean = false,
    val surveyScreenData: SurveyScreenData? = null,
    val firebaseUserData: FirebaseUserData? = null,
    val error: String? = null,
    val isLoading: Boolean = false
)

@HiltViewModel
class SurveyViewModel @Inject constructor(
    private val useCases: UseCases,
    private val firebaseRepository: FirebaseRepository
): ViewModel() {

    private val _surveyUiState = MutableStateFlow(SurveyUiState())
    val surveyUiState = _surveyUiState.asStateFlow()

    private val questionOrder: List<SurveyQuestion> = listOf(
        SurveyQuestion.GOAL,
        SurveyQuestion.GENDER,
        SurveyQuestion.AGE,
        SurveyQuestion.WEIGHT,
        SurveyQuestion.HEIGHT,
        SurveyQuestion.EXERCISE,
        SurveyQuestion.WEIGHT_GOAL,
        SurveyQuestion.TIME_FRAME,
        SurveyQuestion.DIET_TYPE
    )

    init {
        updateSurveyScreenData()
        getSignedInUser()
    }

    fun onBackPressed(): Boolean {
        if (_surveyUiState.value.questionIndex == 0) {
            return false
        }
        changeQuestion(_surveyUiState.value.questionIndex - 1)
        return true
    }

    fun onPreviousPressed() {
        check (_surveyUiState.value.questionIndex == 0) {
            throw IllegalStateException("onPreviousPressed when on question 0")
        }
        changeQuestion(_surveyUiState.value.questionIndex - 1)
    }

    fun onNextPressed() {
        changeQuestion(_surveyUiState.value.questionIndex + 1)
    }

    private fun changeQuestion(newQuestionIndex: Int) {
        _surveyUiState.update { currentState ->
            currentState.copy(
                questionIndex = newQuestionIndex,
                isNextEnabled = getIsNextEnabled(currentState.copy(questionIndex = newQuestionIndex))
            )
        }
        updateSurveyScreenData()
    }

    fun onDonePressed(onSurveyComplete: () -> Unit) {
        onSurveyComplete()
    }

    fun onGoalResponse(goal: ChoiceItem) {
        _surveyUiState.update { currentState ->
            currentState.copy(
                goalResponse = goal,
                isNextEnabled = getIsNextEnabled(currentState.copy(goalResponse = goal))
            )
        }
    }

    fun onGenderResponse(gender: ChoiceItem) {
        _surveyUiState.update { currentState ->
            currentState.copy(
                genderResponse = gender,
                isNextEnabled = getIsNextEnabled(currentState.copy(genderResponse = gender))
            )
        }
    }

    fun onAgeResponse(age: String) {
        _surveyUiState.update { currentState ->
            currentState.copy(
                ageResponse = age,
                isNextEnabled = getIsNextEnabled(currentState.copy(ageResponse = age))
            )
        }
    }

    fun onWeightResponse(weight: String) {
        _surveyUiState.update { currentState ->
            currentState.copy(
                weightResponse = weight,
                isNextEnabled = getIsNextEnabled(currentState.copy(weightResponse = weight))
            )
        }
    }

    fun onHeightResponse(height: String) {
        _surveyUiState.update { currentState ->
            currentState.copy(
                heightResponse = height,
                isNextEnabled = getIsNextEnabled(currentState.copy(heightResponse = height))
            )
        }
    }

    fun onExerciseResponse(exercise: String) {
        _surveyUiState.update { currentState ->
            currentState.copy(
                exerciseResponse = exercise,
                isNextEnabled = getIsNextEnabled(currentState.copy(exerciseResponse = exercise))
            )
        }
    }

    fun onWeightGoalResponse(weightGoal: String) {
        _surveyUiState.update { currentState ->
            currentState.copy(
                weightGoalResponse = weightGoal,
                isNextEnabled = getIsNextEnabled(currentState.copy(weightGoalResponse = weightGoal))
            )
        }
    }

    fun onTimeFrameResponse(timeFrame: String) {
        _surveyUiState.update { currentState ->
            currentState.copy(
                timeFrameResponse = timeFrame,
                isNextEnabled = getIsNextEnabled(currentState.copy(timeFrameResponse = timeFrame))
            )
        }
    }

    fun onDietTypeResponse(dietType: ChoiceItem) {
        _surveyUiState.update { currentState ->
            currentState.copy(
                dietTypeResponse = dietType,
                isNextEnabled = getIsNextEnabled(currentState.copy(dietTypeResponse = dietType))
            )
        }
    }

    private fun getIsNextEnabled(state: SurveyUiState): Boolean {
        return when(questionOrder[state.questionIndex]) {
            SurveyQuestion.GOAL -> state.goalResponse != null
            SurveyQuestion.GENDER -> state.genderResponse != null
            SurveyQuestion.AGE -> state.ageResponse != ""
            SurveyQuestion.WEIGHT -> state.weightResponse != ""
            SurveyQuestion.HEIGHT -> state.heightResponse != ""
            SurveyQuestion.EXERCISE -> state.exerciseResponse != ""
            SurveyQuestion.WEIGHT_GOAL -> state.weightGoalResponse != ""
            SurveyQuestion.TIME_FRAME -> state.timeFrameResponse != ""
            SurveyQuestion.DIET_TYPE -> state.dietTypeResponse != null
        }
    }

    private fun updateSurveyScreenData() {
        _surveyUiState.update { currentState ->
            currentState.copy(
                surveyScreenData = SurveyScreenData(
                    questionIndex = currentState.questionIndex,
                    questionCount = questionOrder.size,
                    shouldShowPreviousButton = currentState.questionIndex > 0,
                    shouldShowDoneButton = currentState.questionIndex == questionOrder.size - 1,
                    surveyQuestion = questionOrder[currentState.questionIndex]
                )
            )
        }
    }

    fun saveUserInfo(
        id: Int,
        name: String,
        image: String,
        goal: String,
        weight: String,
        height: String,
        age: String,
        gender: String,
        exerciseDayInAWeek: String,
        weightGoal: String,
        timeFrame: String,
        diet_type: String
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            useCases.postUserInfoToRemoteUseCase(
                id = id,
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
                diet_type = diet_type
            )
        }
    }

    fun saveSurveyState(completed: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            useCases.saveSurveyUseCase(completed)
        }
    }

    private fun getSignedInUser() {
        viewModelScope.launch {
            _surveyUiState.update { currentState ->
                when (val firebaseUserData = firebaseRepository.getSignedInUser()) {
                    is ApiResult.Success -> {
                        currentState.copy(
                            firebaseUserData = firebaseUserData.data
                        )
                    }
                    is ApiResult.Error -> {
                        currentState.copy(
                            error = firebaseUserData.message ?: "An error occurred"
                        )
                    }
                    is ApiResult.Loading -> {
                        currentState.copy(isLoading = true)
                    }
                }
            }
        }
    }
}

enum class SurveyQuestion {
    GOAL,
    GENDER,
    AGE,
    WEIGHT,
    HEIGHT,
    EXERCISE,
    WEIGHT_GOAL,
    TIME_FRAME,
    DIET_TYPE
}

data class SurveyScreenData(
    val questionIndex: Int,
    val questionCount: Int,
    val shouldShowPreviousButton: Boolean,
    val shouldShowDoneButton: Boolean,
    val surveyQuestion: SurveyQuestion
)