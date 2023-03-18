package com.example.simpleviralgames.presentation.theme.atom

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.heightIn
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.simpleviralgames.presentation.theme.AppShapes
import com.example.simpleviralgames.presentation.theme.height36dp
import com.example.simpleviralgames.presentation.theme.padding24dp
import com.example.simpleviralgames.presentation.theme.padding8dp
import com.example.simpleviralgames.presentation.theme.Black
import com.example.simpleviralgames.presentation.theme.ButtonColor
import com.example.simpleviralgames.presentation.theme.DisabledColor
import com.example.simpleviralgames.presentation.theme.White
import com.example.simpleviralgames.presentation.theme.border2dp

@Composable
fun PrimaryButton(
    label: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true
) {
    OutlinedButton(
        onClick = onClick,
        modifier = modifier.heightIn(height36dp),
        enabled = enabled,
        contentPadding = PaddingValues(
            horizontal = padding24dp,
            vertical = padding8dp
        ),
        shape = AppShapes.extraLarge,
        border = BorderStroke(
            width = border2dp,
            color = Black
        ),
        colors = ButtonDefaults.buttonColors(
            containerColor = ButtonColor,
            disabledContainerColor = ButtonColor
        )
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.titleSmall,
            color = if (enabled) White else DisabledColor
        )
    }
}