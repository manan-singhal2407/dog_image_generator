package com.example.simpleviralgames.presentation.base

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.simpleviralgames.presentation.base.navigation.Navigator
import com.example.simpleviralgames.presentation.base.navigation.Screen
import com.example.simpleviralgames.presentation.theme.SimpleViralGamesTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var navigator: Navigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SimpleViralGamesTheme {
                val navController = rememberNavController()
                NavigationCallBack(navController)
                NavHost(
                    navController = navController,
                    startDestination = Screen.Home.route
                ) {
                    composable(Screen.Home.route) {
                    }
                }
            }
        }
    }

    @Composable
    private fun NavigationCallBack(navController: NavHostController) {
        val destination by navigator.destination.collectAsState()
        LaunchedEffect(destination) {
            if (navController.currentDestination?.route != destination.route) {
                navController.navigate(destination.route) {
                    navigator.popUpTo?.let {
                        popUpTo(it.route) { inclusive = true }
                    }
                }
                navigator.popUpTo = null
            }
        }
    }
}