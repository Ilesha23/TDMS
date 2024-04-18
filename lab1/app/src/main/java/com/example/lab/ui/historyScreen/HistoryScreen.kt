package com.example.lab.ui.historyScreen

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.example.lab.data.HistoryManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Composable
fun HistoryScreen() {
    val context = LocalContext.current
    var currentToast: Toast? by remember { mutableStateOf(null) }
    val coroutineScope = rememberCoroutineScope()

    DisposableEffect(Unit) {
        onDispose {
            currentToast?.cancel()
        }
    }

    HistoryScreenContent(
        coroutineScope = coroutineScope,
        onError = { errorMessage ->
            coroutineScope.launch {
                withContext(Dispatchers.Main) {
                    currentToast?.cancel()
                    currentToast = Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT)
                    currentToast?.show()
                }
            }
        }

    )
}

@Composable
fun HistoryScreenContent(
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    onError: (String) -> Unit
) {
    val scrollState = rememberScrollState()
    val historyManager = HistoryManager(LocalContext.current)

    var text by rememberSaveable { mutableStateOf("") }

    LaunchedEffect(Unit) {
        coroutineScope.launch {
            text = historyManager.read(
                onError = {
                    onError(it)
                }
            )
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
    ) {
        Text(
            text = text
        )
    }
}