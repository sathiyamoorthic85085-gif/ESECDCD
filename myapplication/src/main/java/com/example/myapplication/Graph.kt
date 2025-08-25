// File: com/example/dressmonitor/navigation/AuthScreen.kt
package com.example.dressmonitor.navigation

sealed class AuthScreen(val route: String) {
    object Login : AuthScreen("login_screen")
    object SignUp : AuthScreen("signup_screen")
    object ForgotPassword : AuthScreen("forgot_password_screen")
}
