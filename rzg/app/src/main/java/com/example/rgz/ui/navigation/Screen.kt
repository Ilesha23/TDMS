package com.example.rgz.ui.navigation

sealed class Screen(val route: String) {
    data object Search: Screen("search")
    data object Update: Screen("update")
    data object Full: Screen("full")
}