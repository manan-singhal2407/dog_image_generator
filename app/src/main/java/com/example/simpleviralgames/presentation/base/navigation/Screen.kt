package com.example.simpleviralgames.presentation.base.navigation

sealed class Screen(override val route: String) : NavigationDestination {
    object Home : Screen("home_screen")
    object GenerateDogs : Screen("generate_dogs_screen")
    object PreviewDogs : Screen("preview_dogs_screen")
}