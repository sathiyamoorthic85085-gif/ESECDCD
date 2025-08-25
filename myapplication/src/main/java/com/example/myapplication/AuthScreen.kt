package com.example.dressmonitor.navigation

sealed class AuthScreen(val route: String) {
    object Login : AuthScreen("login_screen")
    object SignUp : AuthScreen("signup_screen")
    object Forgot : AuthScreen("forgot_password_screen")
    // Add any other auth-related screens here if needed
}
