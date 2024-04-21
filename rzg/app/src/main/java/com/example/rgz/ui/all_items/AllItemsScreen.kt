package com.example.rgz.ui.all_items

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.rgz.data.db.Car

@Composable
fun AllItemsScreen(
    viewModel: AllItemsViewModel = hiltViewModel()
) {
    val items = viewModel.items.collectAsState().value
    AllItemsScreenContent(items)
}

@Composable
fun AllItemsScreenContent(
    items: List<Car>
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFFE1CBFF),
                        Color(0xFFB178FF)
                    )
                )
            )
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(
            15.dp,
            Alignment.Top
        )
    ) {
        items.forEach {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
            ) {
                Text(
                    modifier = Modifier
                        .weight(1f),
                    text = it.name
                )
                Text(
                    modifier = Modifier
                        .weight(3f),
                    text = it.description
                )
                Text(
                    modifier = Modifier
                        .weight(1f),
                    text = it.price.toString()
                )
            }
        }
    }
}