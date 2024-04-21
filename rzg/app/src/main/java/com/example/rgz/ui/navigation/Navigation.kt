package com.example.rgz.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.rgz.ui.all_items.AllItemsScreen
import com.example.rgz.ui.search.SearchScreen
import com.example.rgz.ui.update.UpdateScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.Search.route) {

        composable(Screen.Search.route) {
            SearchScreen(
                onUpdate = {
                    navController.navigate(Screen.Update.route)
                },
                onShowClick = {
                    navController.navigate(Screen.Full.route)
                }
            )
        }

        composable(Screen.Update.route) {
            UpdateScreen()
        }

        composable(Screen.Full.route) {
            AllItemsScreen()
        }

    }
}