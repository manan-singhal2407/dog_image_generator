package com.example.simpleviralgames.presentation.screen.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavHostController
import com.example.simpleviralgames.R
import com.example.simpleviralgames.presentation.base.navigation.Screen
import com.example.simpleviralgames.presentation.theme.Black
import com.example.simpleviralgames.presentation.theme.White
import com.example.simpleviralgames.presentation.theme.atom.PrimaryButton

@Composable
fun HomeScreen(
    navController: NavHostController
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = White)
    ) {
        Text(
            text = String.format(
                stringResource(id = R.string.home_screen_text)
            ),
            style = MaterialTheme.typography.titleMedium,
            color = Black,
            textAlign = TextAlign.Center
        )
        PrimaryButton(
            label = String.format(
                stringResource(id = R.string.generate_dogs_screen_text)
            ),
            onClick = {
                navController.navigate(Screen.GenerateDogs.route)
            }
        )
        PrimaryButton(
            label = String.format(
                stringResource(id = R.string.preview_dogs_screen_text)
            ),
            onClick = {
                navController.navigate(Screen.PreviewDogs.route)
            }
        )
    }
}