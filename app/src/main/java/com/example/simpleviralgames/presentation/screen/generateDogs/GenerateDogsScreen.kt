package com.example.simpleviralgames.presentation.screen.generateDogs

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.simpleviralgames.R
import com.example.simpleviralgames.presentation.theme.White
import com.example.simpleviralgames.presentation.theme.atom.PrimaryAppBar
import com.example.simpleviralgames.presentation.theme.atom.PrimaryButton

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GenerateDogsScreen(
    navController: NavHostController,
    viewModel: GenerateDogsViewModel
) {
    val context = LocalContext.current
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp

    Scaffold(
        topBar = {
            PrimaryAppBar(
                title = String.format(
                    stringResource(id = R.string.generate_dogs_screen_text)
                ),
                onClick = {
                    navController.popBackStack()
                }
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = White),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            AsyncImage(
                modifier = Modifier
                    .width(screenWidth - 100.dp)
                    .height(screenWidth - 100.dp),
                model = viewModel.imageUrl.value,
                contentDescription = null,
                contentScale = ContentScale.Fit
            )
            Spacer(modifier = Modifier.height(50.dp))
            PrimaryButton(
                label = String.format(
                    stringResource(id = R.string.generate_dogs_button_text)
                ),
                onClick = {
                    viewModel.onGenerateButton(context = context)
                },
                enabled = !viewModel.loading.value
            )
        }
    }
}