package com.mo7ammedtabasi.recipeapp.data.repository

import com.mo7ammedtabasi.recipeapp.data.local.MealDao
import com.mo7ammedtabasi.recipeapp.data.local.MealEntity
import com.mo7ammedtabasi.recipeapp.data.model.MealDto
import com.mo7ammedtabasi.recipeapp.data.network.ApiService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RecipeRepository @Inject constructor(
    private val apiService: ApiService,
    private val mealDao: MealDao
) {
    suspend fun getMeals(category: String): List<MealDto> {
        return try {
            val response = apiService.getMealsByCategory(category)
            response.meals ?: emptyList()
        } catch (e: Exception) {
            emptyList()
        }
    }

    val favoriteMeals: Flow<List<MealEntity>> = mealDao.getFavoriteMeals()

    suspend fun addFavorite(meal: MealEntity) = mealDao.insertFavorite(meal)
    suspend fun removeFavorite(mealId: String) = mealDao.deleteFavorite(mealId)
    suspend fun isFavorite(mealId: String): Boolean = mealDao.isFavorite(mealId)

    suspend fun searchMeals(query: String): List<MealDto> {
        return try {
            val response = apiService.searchMeals(query)
            response.meals ?: emptyList()
        } catch (e: Exception) {
            emptyList()
        }
    }

    suspend fun getMealDetails(mealId: String): MealDto? {
        return try {
            // 1. محاولة جلب التفاصيل عبر الإنترنت أونلاين
            val response = apiService.getMealDetails(mealId)
            response.meals?.firstOrNull()
        } catch (e: Exception) {
            // 2. إذا انقطع الإنترنت، نجلبها من قاعدة بيانات Room المحلية
            val localMeal = mealDao.getMealById(mealId)
            localMeal?.let {
                MealDto(
                    id = it.id,
                    name = it.name,
                    imageUrl = it.imageUrl,
                    instructions = it.instructions,
                    category = it.category
                )
            }
        }
    }
}