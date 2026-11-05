package com.dlebal.androidtechnicaltest.ui.core.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun PagingLoadingState(modifier: Modifier = Modifier, progressColor: Color = Color.Red, sizeProgress: Dp = 16.dp, contentAlignment: Alignment = Alignment.Center){
    Box(modifier = modifier, contentAlignment = contentAlignment){
        CircularProgressIndicator(Modifier.size(sizeProgress), color = progressColor)
    }
}