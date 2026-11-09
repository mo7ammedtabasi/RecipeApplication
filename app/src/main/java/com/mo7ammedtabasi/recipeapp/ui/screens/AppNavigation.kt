package com.mo7ammedtabasi.recipeapp.ui.screens

import androidx.compose.runtime.Composable
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.mo7ammedtabasi.recipeapp.ui.viewmodel.RecipeViewModel

@Composable
fun AppNavigation(viewModel: RecipeViewModel) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "home") {
        // الشاشة الرئيسية
        composable("home") {
            HomeScreen(
                viewModel = viewModel,
                onMealClick = { mealId ->
                    navController.navigate("details/$mealId")
                },
                onFavoritesClick = { // <--- أضف هذا الانتقال هنا
                    navController.navigate("favorites")
                },
                onSearchClick = { // 👈 الانتقال إلى شاشة البحث عند الضغط
                    navController.navigate("search")
                }
            )
        }

        // شاشة التفاصيل مع استقبال الـ mealId
        composable(
            route = "details/{mealId}",
            arguments = listOf(navArgument("mealId") { type = NavType.StringType })
        ) { backStackEntry ->
            val mealId = backStackEntry.arguments?.getString("mealId") ?: ""
            val recipeViewModel: RecipeViewModel = hiltViewModel() // استدعاء الـ ViewModel عبر Hilt

            DetailsScreen(
                mealId = mealId,
                viewModel = recipeViewModel, // تمرير الـ viewModel هنا بنجاح
                onBackClick = { navController.popBackStack() }
            )
        }

        composable("favorites") {
            FavoritesScreen(
                viewModel = viewModel,
                onMealClick = { mealId ->
                    navController.navigate("details/$mealId")
                },
                onBackClick = { navController.popBackStack() }
            )
        }

        composable("search") {
            SearchScreen(
                viewModel = viewModel,
                onMealClick = { mealId ->
                    navController.navigate("details/$mealId")
                },
                onBackClick = { navController.popBackStack() }
            )
        }


    }
}