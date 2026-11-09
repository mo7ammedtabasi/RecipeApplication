package com.mo7ammedtabasi.recipeapp.data.network

import com.mo7ammedtabasi.recipeapp.data.model.MealResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    // جلب وجبات حسب القسم (مثلاً: Chicken أو Beef)
    @GET("filter.php")
    suspend fun getMealsByCategory(
        @Query("c") category: String = "Chicken"
    ): MealResponse

    // جلب تفاصيل وجبة معينة حسب الـ ID
    @GET("lookup.php")
    suspend fun getMealDetails(@Query("i") id: String): MealResponse

    @GET("search.php")
    suspend fun searchMeals(@Query("s") query: String): MealResponse
}