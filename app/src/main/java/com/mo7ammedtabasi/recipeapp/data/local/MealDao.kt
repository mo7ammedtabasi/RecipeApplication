package com.mo7ammedtabasi.recipeapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface MealDao {

    // جلب كل الوجبات المفضلة
    @Query("SELECT * FROM favorite_meals")
    fun getFavoriteMeals(): Flow<List<MealEntity>>

    // إضافة وجبة للمفضلة
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(meal: MealEntity)

    // حذف وجبة من المفضلة عبر الـ ID
    @Query("DELETE FROM favorite_meals WHERE id = :mealId")
    suspend fun deleteFavorite(mealId: String)

    // التحقق هل الوجبة موجودة في المفضلة أم لا
    @Query("SELECT EXISTS(SELECT * FROM favorite_meals WHERE id = :mealId)")
    suspend fun isFavorite(mealId: String): Boolean

    @Query("SELECT * FROM favorite_meals WHERE id = :mealId")
    suspend fun getMealById(mealId: String): MealEntity?
}