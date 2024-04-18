package com.example.lab.data

import android.content.Context
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File

class HistoryManager(private val context: Context) {
    private val file = File(context.filesDir, Keys.filename)

    private object Keys {
        const val filename = "history"
    }

    suspend fun write(text: String, onError: (String) -> Unit) {
        withContext(Dispatchers.IO) {
            try {
                file.appendText("$text\n")
            } catch (e: Exception) {
                onError(e.localizedMessage.orEmpty())
            }
        }
    }

    suspend fun read(onError: (String) -> Unit): String {
        return withContext(Dispatchers.IO) {
            var text = ""
            try {
                text = file.readText()
            } catch (e: Exception) {
                onError(e.localizedMessage.orEmpty())
            }
            return@withContext text
        }
    }
}