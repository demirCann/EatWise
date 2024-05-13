package com.demircandemir.relaysample.presentation.screens.search

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.demircandemir.relaysample.domain.model.MealInfo
import com.demircandemir.relaysample.domain.use_cases.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val useCases: UseCases
): ViewModel() {

    private val _searchQuery = mutableStateOf("")
    val searchQuery = _searchQuery


    private val _searchedMeals = MutableStateFlow<PagingData<MealInfo>>(PagingData.empty())
    val searchedMeals = _searchedMeals.asStateFlow()




    fun updateSearchQuery(newQuery: String) {
        _searchQuery.value = newQuery
    }


    fun searchMeals(query: String) {
        viewModelScope.launch(Dispatchers.IO) {
            useCases.searchMealsUseCase(name = query).cachedIn(viewModelScope).collect{
                _searchedMeals.value = it
            }
        }
    }

}