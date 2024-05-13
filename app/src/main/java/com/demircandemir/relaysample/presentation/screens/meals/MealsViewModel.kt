package com.demircandemir.relaysample.presentation.screens.meals

import androidx.lifecycle.ViewModel
import com.demircandemir.relaysample.domain.use_cases.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MealsViewModel @Inject constructor(
    useCases: UseCases
) : ViewModel() {
    val allMeals = useCases.getAllMealsUseCase()
}