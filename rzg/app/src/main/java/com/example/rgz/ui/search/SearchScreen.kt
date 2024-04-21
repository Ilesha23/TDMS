package com.example.rgz.ui.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun SearchScreen(
    viewModel: SearchViewModel = hiltViewModel(),
    onUpdate: () -> Unit = {},
    onShowClick: () -> Unit = {}
) {
    SearchScreenContent(
        onAddClick = { name, descr, price ->
            viewModel.insert(name, descr, price)
            onUpdate()
        },
        onDeleteClick = { name ->
            viewModel.delete(name)
        },
        onShowClick = {
            onShowClick()
        }
    )
}

@Composable
fun SearchScreenContent(
    onAddClick: (String, String, String) -> Unit = { _, _, _ -> },
    onDeleteClick: (String) -> Unit = { _ -> },
    onShowClick: () -> Unit = {}
) {
    var name by rememberSaveable { mutableStateOf("") }
    var descr by rememberSaveable { mutableStateOf("") }
    var price by rememberSaveable { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFFD4FCAE),
                        Color(0xFFBAFFC9)
                    )
                )
            )
            .padding(10.dp),
        verticalArrangement = Arrangement.spacedBy(
            30.dp,
            Alignment.CenterVertically
        )
    ) {
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth(),
            value = name,
            onValueChange = {
                name = it
            },
            label = {
                Text(text = "Name")
            },
            maxLines = 1
        )
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth(),
            value = descr,
            onValueChange = {
                descr = it
            },
            label = {
                Text(text = "Descr")
            },
            maxLines = 1
        )
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth(),
            value = price,
            onValueChange = {
                price = it
            },
            label = {
                Text(text = "Price")
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number
            ),
            maxLines = 1
        )
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(
                10.dp,
                Alignment.CenterHorizontally
            )
        ) {
            Button(
                modifier = Modifier
                    .weight(1f),
                onClick = {
                    onAddClick(name, descr, price)
                }
            ) {
                Text(text = "Add")
            }
            Button(
                modifier = Modifier
                    .weight(1f),
                onClick = {
                    onDeleteClick(name)
                }
            ) {
                Text(text = "Delete")
            }
            Button(
                modifier = Modifier
                    .weight(1f),
                onClick = {
                    onShowClick()
                }
            ) {
                Text(text = "Show")
            }
        }
    }
}