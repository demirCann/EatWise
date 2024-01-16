package com.demircandemir.relaysample.domain.use_cases

import com.demircandemir.relaysample.domain.use_cases.get_all_meals.GetAllMealsUseCase
import com.demircandemir.relaysample.domain.use_cases.get_meals_for_selection.GetMealForSelectionUseCase
import com.demircandemir.relaysample.domain.use_cases.get_selected_meal.GetSelectedMealUseCase
import com.demircandemir.relaysample.domain.use_cases.get_user_info.GetUserInfoUseCase
import com.demircandemir.relaysample.domain.use_cases.post_user_info.PostUserInfoUseCase
import com.demircandemir.relaysample.domain.use_cases.read_survey.ReadSurveyStateUseCases
import com.demircandemir.relaysample.domain.use_cases.save_survey.SaveSurveyUseCase
import com.demircandemir.relaysample.domain.use_cases.search_meals.SearchMealsUseCase

data class UseCases(
    val saveSurveyUseCase: SaveSurveyUseCase,
    val readSurveyUseCase: ReadSurveyStateUseCases,
    val getUserInfoUseCase: GetUserInfoUseCase,
    val postUserInfoUseCase: PostUserInfoUseCase,
    val getAllMealsUseCase: GetAllMealsUseCase,
    val getMealForSelectionUseCase: GetMealForSelectionUseCase,
    val getSelectedMealUseCase: GetSelectedMealUseCase,
    val searchMealsUseCase: SearchMealsUseCase
)
