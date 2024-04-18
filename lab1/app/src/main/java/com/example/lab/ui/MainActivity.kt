package com.example.lab.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.lab.ui.navigation.Navigation
import com.example.lab.ui.theme.LabTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LabTheme {
                Navigation()
            }
        }
    }
}