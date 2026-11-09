package com.mo7ammedtabasi.recipeapp.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_meals")
data class MealEntity(
    @PrimaryKey
    val id: String,
    val name: String,
    val imageUrl: String,
    val instructions: String?,
    val category: String?
)