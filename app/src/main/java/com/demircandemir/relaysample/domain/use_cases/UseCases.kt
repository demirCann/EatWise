package com.demircandemir.relaysample.domain.use_cases

import com.demircandemir.relaysample.domain.use_cases.get_all_meals.GetAllMealsUseCase
import com.demircandemir.relaysample.domain.use_cases.get_daily_meals.GetDailyMealsUseCase
import com.demircandemir.relaysample.domain.use_cases.get_diet_plan.GetDietPlanUseCase
import com.demircandemir.relaysample.domain.use_cases.get_meals_for_selection.GetMealForSelectionUseCase
import com.demircandemir.relaysample.domain.use_cases.get_selected_meal.GetSelectedMealUseCase
import com.demircandemir.relaysample.domain.use_cases.get_user_info.GetUserInfoFromRemoteUseCase
import com.demircandemir.relaysample.domain.use_cases.post_diet_plan.PostDietPlanUseCase
import com.demircandemir.relaysample.domain.use_cases.post_user_info.PostUserInfoToRemoteUseCase
import com.demircandemir.relaysample.domain.use_cases.read_survey.ReadSurveyStateUseCases
import com.demircandemir.relaysample.domain.use_cases.save_survey.SaveSurveyUseCase
import com.demircandemir.relaysample.domain.use_cases.search_meals.SearchMealsUseCase
import com.demircandemir.relaysample.domain.use_cases.upate_diet_plan.UpdateDietPlanUseCase

data class UseCases(
    val saveSurveyUseCase: SaveSurveyUseCase,
    val readSurveyUseCase: ReadSurveyStateUseCases,
    val getUserInfoFromRemoteUseCase: GetUserInfoFromRemoteUseCase,
    val postUserInfoToRemoteUseCase: PostUserInfoToRemoteUseCase,
    val getAllMealsUseCase: GetAllMealsUseCase,
    val getMealForSelectionUseCase: GetMealForSelectionUseCase,
    val getSelectedMealUseCase: GetSelectedMealUseCase,
    val searchMealsUseCase: SearchMealsUseCase,
    val getDietPlanUseCase: GetDietPlanUseCase,
    val postDietPlanUseCase: PostDietPlanUseCase,
    val getDailyMealsUseCase: GetDailyMealsUseCase,
    val updateDietPlanUseCase: UpdateDietPlanUseCase
)