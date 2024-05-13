package com.demircandemir.relaysample.presentation.screens.survey

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.demircandemir.relaysample.presentation.screens.survey.question.ChoiceItem



class SurveyViewModel(): ViewModel() {


    private val questionOrder: List<SurveyQuestion> = listOf(
        SurveyQuestion.GOAL,
        SurveyQuestion.GENDER,
        SurveyQuestion.AGE,
        SurveyQuestion.WEIGHT,
        SurveyQuestion.HEIGHT,
        SurveyQuestion.EXERCISE,
    )

    private var questionIndex = 0

    // ----- Responses exposed as State -----

    private val _goalResponse = mutableStateOf<ChoiceItem?>(null)
    val goalResponse: ChoiceItem?
        get() = _goalResponse.value

    private val _genderResponse = mutableStateOf<ChoiceItem?>(null)
    val genderResponse: ChoiceItem?
        get() = _genderResponse.value

    private val _ageResponse = mutableStateOf("")
    val ageResponse: String
        get() = _ageResponse.value

    private val _weightResponse = mutableStateOf("")
    val weightResponse: String
        get() = _weightResponse.value

    private val _heightResponse = mutableStateOf("")
    val heightResponse: String
        get() = _heightResponse.value

    private val _exerciseResponse = mutableStateOf("")
    val exerciseResponse: String
        get() = _exerciseResponse.value


    // ----- Survey status exposed as State -----

    private val _surveyScreenData = mutableStateOf(createSurveyScreenData())
    val surveyScreenData: SurveyScreenData?
        get() = _surveyScreenData.value

    private val _isNextEnabled = mutableStateOf(false)
    val isNextEnabled: Boolean
        get() = _isNextEnabled.value

    fun onBackPressed(): Boolean {
        if (questionIndex == 0) {
            return false
        }
        changeQuestion(questionIndex - 1)
        return true
    }

    fun onPreviousPressed() {
        check (questionIndex == 0) {
            throw IllegalStateException("onPreviousPressed when on question 0")
        }
        changeQuestion(questionIndex - 1)
    }

    fun onNextPressed() {
        changeQuestion(questionIndex + 1)
    }

    private fun changeQuestion(newQuestionIndex: Int) {
        questionIndex = newQuestionIndex
        _isNextEnabled.value = getIsNextEnabled()
        _surveyScreenData.value = createSurveyScreenData()
    }

    fun onDonePressed(onSurveyComplete: () -> Unit) {
        onSurveyComplete()
    }

    fun onGoalResponse(goal: ChoiceItem) {
        _goalResponse.value = goal
        _isNextEnabled.value = getIsNextEnabled()
    }

    fun onGenderResponse(gender: ChoiceItem) {
        _genderResponse.value = gender
        _isNextEnabled.value = getIsNextEnabled()
    }

    fun onAgeResponse(age: String) {
        _ageResponse.value = age
        _isNextEnabled.value = getIsNextEnabled()
    }

    fun onWeightResponse(weight: String) {
        _weightResponse.value = weight
        _isNextEnabled.value = getIsNextEnabled()
    }

    fun onHeightResponse(height: String) {
        _heightResponse.value = height
        _isNextEnabled.value = getIsNextEnabled()
    }

    fun onExerciseResponse(exercise: String) {
        _exerciseResponse.value = exercise
        _isNextEnabled.value = getIsNextEnabled()
    }




    private fun getIsNextEnabled(): Boolean {
        return when(questionOrder[questionIndex]) {
            SurveyQuestion.GOAL -> _goalResponse.value != null
            SurveyQuestion.GENDER -> _genderResponse.value != null
            SurveyQuestion.AGE -> _ageResponse.value != ""
            SurveyQuestion.WEIGHT -> _weightResponse.value != ""
            SurveyQuestion.HEIGHT -> _heightResponse.value != ""
            SurveyQuestion.EXERCISE -> _exerciseResponse.value != ""
        }
    }

    private fun createSurveyScreenData(): SurveyScreenData {
        return SurveyScreenData(
            questionIndex = questionIndex,
            questionCount = questionOrder.size,
            shouldShowPreviousButton = questionIndex > 0,
            shouldShowDoneButton = questionIndex == questionOrder.size - 1,
            surveyQuestion = questionOrder[questionIndex]
        )
    }
}





enum class SurveyQuestion {
    GOAL,
    GENDER,
    AGE,
    WEIGHT,
    HEIGHT,
    EXERCISE,
}

data class SurveyScreenData(
    val questionIndex: Int,
    val questionCount: Int,
    val shouldShowPreviousButton: Boolean,
    val shouldShowDoneButton: Boolean,
    val surveyQuestion: SurveyQuestion
)
