package com.mo7ammedtabasi.recipeapp.data.model

import com.google.gson.annotations.SerializedName

// الاستجابة القادمة من الـ API
data class MealResponse(
    @SerializedName("meals")
    val meals: List<MealDto>?
)

// تفاصيل كل وجبة من الـ API
data class MealDto(
    @SerializedName("idMeal")
    val id: String,
    @SerializedName("strMeal")
    val name: String,
    @SerializedName("strMealThumb")
    val imageUrl: String,
    @SerializedName("strInstructions")
    val instructions: String? = null,
    @SerializedName("strCategory")
    val category: String? = null
)