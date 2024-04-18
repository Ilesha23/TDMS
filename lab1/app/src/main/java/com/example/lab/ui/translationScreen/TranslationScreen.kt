package com.example.lab.ui.translationScreen

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.example.lab.data.HistoryManager
import kotlinx.coroutines.launch

@Composable
fun TranslationScreen(onHistoryClick: () -> Unit = {}) {
    val context = LocalContext.current
    var currentToast: Toast? by remember { mutableStateOf(null) }

    DisposableEffect(Unit) {
        onDispose {
            currentToast?.cancel()
        }
    }

    TranslationScreenContent(
        onHistoryClick = {
            onHistoryClick()
        },
        onError = {
            currentToast?.cancel()
            currentToast = Toast.makeText(context, it, Toast.LENGTH_SHORT)
            currentToast?.show()
        }
    )
}


@Composable
fun TranslationScreenContent(
    onHistoryClick: () -> Unit,
    onError: (String) -> Unit
) {
    val coroutineScope = rememberCoroutineScope()
    var text by rememberSaveable { mutableStateOf("") }
    var number by rememberSaveable { mutableStateOf("") }
    val historyManager = HistoryManager(LocalContext.current)

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        TextField(
            modifier = Modifier
                .fillMaxWidth(),
            value = text,
            onValueChange = { text = it }
        )
        Button(
            modifier = Modifier
                .fillMaxWidth(),
            onClick = {
                number = convertToPhoneNumber(text)
                coroutineScope.launch {
                    historyManager.write(
                        text = number,
                        onError = {

                        }
                    )
                }
            }
        ) {
            Text(text = "Translate") // resources
        }
        Text(
            modifier = Modifier
                .fillMaxWidth(),
            text = number
        )
        Button(
            modifier = Modifier
                .fillMaxWidth(),
            onClick = {
                onHistoryClick()
            }
        ) {
            Text(text = "History") // resources
        }
    }
}

fun convertToPhoneNumber(input: String): String {
    val dictionary = mapOf(
        'a' to "2", 'b' to "2", 'c' to "2",
        'd' to "3", 'e' to "3", 'f' to "3",
        'g' to "4", 'h' to "4", 'i' to "4",
        'j' to "5", 'k' to "5", 'l' to "5",
        'm' to "6", 'n' to "6", 'o' to "6",
        'p' to "7", 'q' to "7", 'r' to "7", 's' to "7",
        't' to "8", 'u' to "8", 'v' to "8",
        'w' to "9", 'x' to "9", 'y' to "9", 'z' to "9"
    )
    val phoneNumber = StringBuilder()

    for (s in input) {
        if (!s.isDigit()) {
            if (s in dictionary) {
                phoneNumber.append(dictionary[s])
            } else {
                phoneNumber.append(s)
            }
        } else {
            phoneNumber.append(s)
        }
    }

    return phoneNumber.toString()
}
