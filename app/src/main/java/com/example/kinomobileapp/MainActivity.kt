package com.example.kinomobileapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.kinomobileapp.ui.components.MovieCard
import com.example.kinomobileapp.ui.navigation.AppNavigation
import com.example.kinomobileapp.ui.screens.AddScreen
import com.example.kinomobileapp.ui.screens.LoginScreen
import com.example.kinomobileapp.ui.screens.MovieCatalogScreen
import com.example.kinomobileapp.ui.theme.KinomobileappTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            KinomobileappTheme {
                Surface (
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ){
//                    val navController = rememberNavController()
//                    AppNavigation(navController)
                    AddScreen()
                }
            }
        }
    }
}
