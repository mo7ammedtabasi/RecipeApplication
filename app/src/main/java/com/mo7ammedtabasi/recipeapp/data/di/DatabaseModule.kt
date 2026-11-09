package com.mo7ammedtabasi.recipeapp.data.di

import android.content.Context
import androidx.room.Room
import com.mo7ammedtabasi.recipeapp.data.local.MealDao
import com.mo7ammedtabasi.recipeapp.data.local.RecipeDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): RecipeDatabase {
        return Room.databaseBuilder(
            context,
            RecipeDatabase::class.java,
            "recipe_db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideMealDao(database: RecipeDatabase): MealDao {
        return database.mealDao
    }
}