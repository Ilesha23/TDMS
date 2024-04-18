package com.example.lab.ui.navigation

sealed class Screen(val route: String) {

    data object Translation : Screen("translation")
    data object History : Screen("history")

}