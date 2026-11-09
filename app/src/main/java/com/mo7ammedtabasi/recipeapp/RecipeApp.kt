package com.mo7ammedtabasi.recipeapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class RecipeApp : Application() {
    // يمكن تركها فارغة، وظيفتها الأساسية تفعيل توليد كود Hilt للتطبيق
}