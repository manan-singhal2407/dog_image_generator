package com.example.simpleviralgames.presentation.base

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.simpleviralgames.presentation.base.navigation.Screen
import com.example.simpleviralgames.presentation.screen.generateDogs.GenerateDogsScreen
import com.example.simpleviralgames.presentation.screen.home.HomeScreen
import com.example.simpleviralgames.presentation.theme.SimpleViralGamesTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SimpleViralGamesTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = Screen.Home.route
                ) {
                    composable(Screen.Home.route) {
                        HomeScreen(navController = navController)
                    }
                    composable(Screen.GenerateDogs.route) {
                        GenerateDogsScreen(navController = navController, viewModel = hiltViewModel())
                    }
                }
            }
        }
    }
}