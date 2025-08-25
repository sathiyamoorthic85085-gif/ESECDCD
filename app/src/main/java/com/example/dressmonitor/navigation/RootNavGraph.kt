package com.example.dressmonitor.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.dressmonitor.ui.home.HomeScreen
import com.example.dressmonitor.ui.profile.ProfileScreen
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun RootNavGraph(navController: NavHostController = rememberNavController()) {
    NavHost(
        navController = navController,
        startDestination = "home"
    ) {
        composable("home") {
            HomeScreen(
                userName = "Guest",
                onNavigate = { route -> navController.navigate(route) }
            )
        }

        composable("profile") {
            ProfileScreen()
        }
    }
}
