package com.example.simpleviralgames.presentation.theme.atom

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIos
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import com.example.simpleviralgames.R
import com.example.simpleviralgames.presentation.theme.AppBarColor
import com.example.simpleviralgames.presentation.theme.AppTypography
import com.example.simpleviralgames.presentation.theme.Black
import com.example.simpleviralgames.presentation.theme.ButtonColor
import com.example.simpleviralgames.presentation.theme.height50dp
import com.example.simpleviralgames.presentation.theme.offsetNegative8dp
import com.example.simpleviralgames.presentation.theme.width8dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PrimaryAppBar(
    title: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    CenterAlignedTopAppBar(
        modifier = modifier.height(height50dp),
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = AppBarColor
        ),
        navigationIcon = {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxHeight()
                    .clickable(
                        onClick = onClick,
                        interactionSource = MutableInteractionSource(),
                        indication = null,
                    )
            ) {
                Row {
                    Spacer(modifier = Modifier.width(width8dp))
                    Icon(
                        Icons.Filled.ArrowBackIos,
                        contentDescription = null,
                        tint = ButtonColor
                    )
                    Text(
                        text = String.format(
                            stringResource(id = R.string.back_text)
                        ),
                        modifier = Modifier.offset(x = offsetNegative8dp),
                        style = AppTypography.titleMedium,
                        color = ButtonColor,
                        textAlign = TextAlign.Center
                    )
                }
            }
        },
        title = {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxHeight()
            ) {
                Text(
                    text = title,
                    style = AppTypography.titleMedium,
                    color = Black,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    )
}