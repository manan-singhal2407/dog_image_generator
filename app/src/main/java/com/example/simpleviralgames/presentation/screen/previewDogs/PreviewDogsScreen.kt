package com.example.simpleviralgames.presentation.screen.previewDogs

import android.graphics.BitmapFactory
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.simpleviralgames.R
import com.example.simpleviralgames.presentation.theme.White
import com.example.simpleviralgames.presentation.theme.atom.PrimaryAppBar
import com.example.simpleviralgames.presentation.theme.atom.PrimaryButton

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PreviewDogsScreen(
    navController: NavHostController,
    viewModel: PreviewDogsViewModel
) {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp

    Scaffold(
        topBar = {
            PrimaryAppBar(
                title = String.format(
                    stringResource(id = R.string.preview_dogs_screen_text)
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
                .background(color = White)
                .padding(top = 100.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            if (viewModel.dogsList.size != 0) {
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(count = viewModel.dogsList.size) { index ->
                        Image(
                            modifier = Modifier
                                .width(screenWidth - 40.dp)
                                .height(screenWidth - 40.dp),
                            bitmap = BitmapFactory.decodeByteArray(
                                viewModel.dogsList[index].imageData,
                                0,
                                viewModel.dogsList[index].imageData.size
                            ).asImageBitmap(),
                            contentDescription = null,
                            contentScale = ContentScale.Crop
                        )
                    }
                }
            } else {
                Box(
                    modifier = Modifier
                        .width(screenWidth - 40.dp)
                        .height(screenWidth - 40.dp)
                )
            }
            Spacer(modifier = Modifier.height(50.dp))
            PrimaryButton(
                label = String.format(
                    stringResource(id = R.string.preview_dogs_button_text)
                ),
                onClick = {
                    viewModel.onClearDogsButton()
                },
                enabled = viewModel.dogsList.size != 0
            )
        }
    }
}