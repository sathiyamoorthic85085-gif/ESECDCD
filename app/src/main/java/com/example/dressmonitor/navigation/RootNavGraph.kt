package com.example.dressmonitor.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun RootNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = "home"
    ) {
        composable("home") {
            // TODO: Replace with your HomeScreen
            HomeScreen()
        }

        composable("settings") {
            // TODO: Replace with your SettingsScreen
            SettingsScreen()
        }

        composable("profile") {
            // TODO: Replace with your ProfileScreen
            ProfileScreen()
        }
    }
}
