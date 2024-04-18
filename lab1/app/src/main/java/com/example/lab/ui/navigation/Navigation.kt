package com.example.lab.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.lab.ui.historyScreen.HistoryScreen
import com.example.lab.ui.translationScreen.TranslationScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.Translation.route) {
        composable(Screen.Translation.route) {
            TranslationScreen(
                onHistoryClick = {
                    navController.performIfCurrentDestinationDoesntMatch(
                        secondDestination = Screen.History.route,
                        action = {
                            navigate(Screen.History.route)
                        }
                    )
                }
            )
        }
        composable(Screen.History.route) {
            HistoryScreen()
        }
    }
}

fun NavController.performIfCurrentDestinationDoesntMatch(
    secondDestination: String,
    action: NavController.() -> Unit
) {
    if (currentDestination?.route != secondDestination) {
        action()
    }
}