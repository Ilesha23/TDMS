package com.example.rgz.ui.update

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun UpdateScreen(
    viewModel: UpdateViewModel = hiltViewModel()
) {
    val info = viewModel.carInfo.collectAsState().value
    UpdateScreenContent(info)
}

@Composable
fun UpdateScreenContent(
    list: List<String>
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFFB1D5FA),
                        Color(0xFF66AEF7)
                    )
                )
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(
            30.dp,
            Alignment.CenterVertically
        )
    ) {
        list.forEach {
            Text(
                text = it,
                style = TextStyle(
                    fontSize = 30.sp
                )
            )
        }
    }
}