package com.example.dressmonitor.ui.screens  // Adjust your package

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.myapplication.ui.auth.AuthViewModel
import com.example.myapplication.ui.screens.LoginScreen
import com.example.myapplication.ui.screens.SignUpScreen

fun NavGraphBuilder.authNavGraph(
    navController: NavHostController,
    authViewModel: AuthViewModel // Receive AuthViewModel
) {
    navigation(
        route = Graph.AUTHENTICATION,
        startDestination = AuthScreen.Login.route
    ) {
        composable(AuthScreen.Login.route) {
            LoginScreen( // Pass them here
                navController = navController,
                authViewModel = authViewModel
            )
        }
        composable(AuthScreen.SignUp.route) {
            SignUpScreen( // Pass them here
                navController = navController,
                authViewModel = authViewModel
            )
        }
    }
}
