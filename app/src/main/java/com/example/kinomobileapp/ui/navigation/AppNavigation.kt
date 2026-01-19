package com.example.kinomobileapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.kinomobileapp.ui.screens.AddScreen
import com.example.kinomobileapp.ui.screens.LoginScreen
import com.example.kinomobileapp.ui.screens.MovieCatalogScreen
import com.example.kinomobileapp.ui.screens.UpdateScreen
import com.example.kinomobileapp.ui.viewmodels.MovieViewModel

@Composable
fun AppNavigation(navController: NavHostController) {
    val movieViewModel: MovieViewModel = hiltViewModel()

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
            MovieCatalogScreen(
                navController = navController,
                viewModel = movieViewModel
            )
        }

        composable ("add_screen") {
            AddScreen(
                navController = navController,
                onMovieAdded = {
                    movieViewModel.loadMovies()
                }
            )
        }

        // ЭКРАН РЕДАКТИРОВАНИЯ С ПАРАМЕТРОМ
        composable(
            route = "update_screen/{movieId}",
            arguments = listOf(navArgument("movieId") { type = NavType.StringType })
        ) { backStackEntry ->
            val movieId = backStackEntry.arguments?.getString("movieId") ?: ""

            UpdateScreen(
                navController = navController,
                movieId = movieId,
                onMovieUpdated = {
                    movieViewModel.loadMovies()
                }
            )
        }
    }
}