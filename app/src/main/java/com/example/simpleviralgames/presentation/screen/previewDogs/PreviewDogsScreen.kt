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
import androidx.compose.material3.Divider
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
import com.example.simpleviralgames.presentation.theme.DisabledColor
import com.example.simpleviralgames.presentation.theme.White
import com.example.simpleviralgames.presentation.theme.atom.PrimaryAppBar
import com.example.simpleviralgames.presentation.theme.atom.PrimaryButton

private val ImageMarginToScreen = 40.dp
private val DividerPaddingTop = 50.dp
private val DividerHeight = 0.4.dp
private val SpaceBetweenImageAndButton = 50.dp
private val HorizontalSpaceBetweenImages = 12.dp
private val ContentPaddingTop = 100.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PreviewDogsScreen(
    navController: NavHostController,
    viewModel: PreviewDogsViewModel
) {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val imageWidth = screenWidth - ImageMarginToScreen

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
                .background(color = White),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Divider(
                modifier = Modifier
                    .padding(top = DividerPaddingTop)
                    .height(DividerHeight)
                    .background(color = DisabledColor)
            )
            if (viewModel.dogsList.size != 0) {
                LazyRow(
                    modifier = Modifier.padding(top = ContentPaddingTop),
                    horizontalArrangement = Arrangement.spacedBy(HorizontalSpaceBetweenImages)
                ) {
                    items(count = viewModel.dogsList.size) { index ->
                        Image(
                            modifier = Modifier
                                .width(imageWidth)
                                .height(imageWidth),
                            bitmap = BitmapFactory.decodeByteArray(
                                viewModel.dogsList[index].imageData,
                                0,
                                viewModel.dogsList[index].imageData.size
                            ).asImageBitmap(),
                            contentDescription = null,
                            contentScale = ContentScale.FillBounds
                        )
                    }
                }
            } else {
                Box(
                    modifier = Modifier
                        .width(imageWidth)
                        .height(imageWidth)
                        .padding(top = ContentPaddingTop)
                )
            }
            Spacer(modifier = Modifier.height(SpaceBetweenImageAndButton))
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