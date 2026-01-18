package com.example.kinomobileapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.kinomobileapp.ui.screens.LoginScreen
import com.example.kinomobileapp.ui.screens.MovieCatalogScreen

@Composable
fun AppNavigation(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = "login"
    ) {
        composable("login"){
            LoginScreen(
                navController = navController,
                onLoginSuccess = {
                    navController.navigate("movie_catalog") {
                        popUpTo("login") {inclusive = true}
                    }
                }
            )
        }

        composable("movie_catalog") {
            MovieCatalogScreen()
        }
    }
}