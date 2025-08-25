package com.example.dressmonitor.ui.screens  // Adjust your package

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.myapplication.ui.auth.AuthViewModel
import com.example.myapplication.ui.screens.MainAppScreen
import com.example.myapplication.ui.screens.SplashScreen

@Composable
fun RootNavGraph(
    navController: NavHostController,
    authViewModel: AuthViewModel // Receive AuthViewModel
) {
    NavHost(
        navController = navController,
        route = Graph.ROOT,
        startDestination = Graph.SPLASH
    ) {
        composable(Graph.SPLASH) {
            SplashScreen( // Pass them here
                navController = navController,
                authViewModel = authViewModel
            )
        }
        authNavGraph(navController = navController, authViewModel = authViewModel) // Pass authViewModel down
        composable(Graph.MAIN_APP) {
            MainAppScreen(
                // If MainAppScreen needs logout, pass authViewModel:
                // authViewModel = authViewModel
            )
        }
    }