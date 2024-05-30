package com.demircandemir.relaysample.presentation.screens.survey

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.demircandemir.relaysample.domain.model.UserInfo
import com.demircandemir.relaysample.domain.use_cases.UseCases
import com.demircandemir.relaysample.presentation.screens.survey.question.ChoiceItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SurveyViewModel @Inject constructor(
    private val useCases: UseCases
): ViewModel() {


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

    private val _weightGoalResponse = mutableStateOf("")
    val weightGoalResponse: String
        get() = _weightGoalResponse.value

    private val _timeFrameResponse = mutableStateOf("")
    val timeFrameResponse: String
        get() = _timeFrameResponse.value

    private val _dietTypeResponse = mutableStateOf<ChoiceItem?>(null)
    val dietTypeResponse: ChoiceItem?
        get() = _dietTypeResponse.value


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

    fun onWeightGoalResponse(weightGoal: String) {
        _weightGoalResponse.value = weightGoal
        _isNextEnabled.value = getIsNextEnabled()
    }

    fun onTimeFrameResponse(timeFrame: String) {
        _timeFrameResponse.value = timeFrame
        _isNextEnabled.value = getIsNextEnabled()
    }

    fun onDietTypeResponse(dietType: ChoiceItem) {
        _dietTypeResponse.value = dietType
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
            SurveyQuestion.WEIGHT_GOAL -> _weightGoalResponse.value != ""
            SurveyQuestion.TIME_FRAME -> _timeFrameResponse.value != ""
            SurveyQuestion.DIET_TYPE -> _dietTypeResponse.value != null

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
            useCases.postUserInfoUseCase(
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
