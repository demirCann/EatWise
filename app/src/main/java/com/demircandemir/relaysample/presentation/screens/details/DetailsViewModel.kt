package com.demircandemir.relaysample.presentation.screens.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.demircandemir.relaysample.domain.model.MealInfo
import com.demircandemir.relaysample.domain.use_cases.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val useCases: UseCases,
    savedStateHandle: SavedStateHandle
) : ViewModel() {



        val mealInfo = savedStateHandle.get<MealInfo>("mealInfo")



}