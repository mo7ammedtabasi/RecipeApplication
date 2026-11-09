package com.mo7ammedtabasi.recipeapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mo7ammedtabasi.recipeapp.data.local.MealEntity
import com.mo7ammedtabasi.recipeapp.data.model.MealDto
import com.mo7ammedtabasi.recipeapp.data.repository.RecipeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipeViewModel @Inject constructor(
    private val repository: RecipeRepository
) : ViewModel() {

    private val _meals = MutableStateFlow<List<MealDto>>(emptyList())
    val meals: StateFlow<List<MealDto>> = _meals

    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _isFavorite = MutableStateFlow(false)
    val isFavorite: StateFlow<Boolean> = _isFavorite

    private val _searchResults = MutableStateFlow<List<MealDto>>(emptyList())
    val searchResults: StateFlow<List<MealDto>> = _searchResults

    private val _selectedMeal = MutableStateFlow<MealDto?>(null)
    val selectedMeal: StateFlow<MealDto?> = _selectedMeal

    private val _isLoadingDetails = MutableStateFlow(false)
    val isLoadingDetails: StateFlow<Boolean> = _isLoadingDetails


    fun getMealDetails(mealId: String) {
        viewModelScope.launch {
            _isLoadingDetails.value = true
            _selectedMeal.value = repository.getMealDetails(mealId)
            _isLoadingDetails.value = false
            if (_selectedMeal.value != null) {
                checkFavoriteStatus(mealId)
            }
        }
    }


    fun searchMeals(query: String) {
        if (query.isBlank()) {
            _searchResults.value = emptyList()
            return
        }
        viewModelScope.launch {
            _searchResults.value = repository.searchMeals(query)
        }
    }


    fun checkFavoriteStatus(mealId: String) {
        viewModelScope.launch {
            _isFavorite.value = repository.isFavorite(mealId)
        }
    }

    val favoriteMeals: StateFlow<List<MealEntity>> = repository.favoriteMeals
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )


    fun removeFavorite(mealId: String) {
        viewModelScope.launch {
            repository.removeFavorite(mealId)
        }
    }


    fun toggleFavorite(meal: MealDto) {
        viewModelScope.launch {
            val entity = MealEntity(
                id = meal.id,
                name = meal.name,
                imageUrl = meal.imageUrl,
                instructions = meal.instructions,
                category = meal.category
            )
            if (_isFavorite.value) {
                repository.removeFavorite(meal.id)
                _isFavorite.value = false
            } else {
                repository.addFavorite(entity)
                _isFavorite.value = true
            }
        }
    }

    init {
        fetchMeals("Chicken")
    }

    fun fetchMeals(category: String) {
        viewModelScope.launch {
            _isLoading.value = true
            _meals.value = repository.getMeals(category)
            _isLoading.value = false
        }
    }
}