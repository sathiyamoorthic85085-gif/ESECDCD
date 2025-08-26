package com.example.dressmonitor.navigation

// import com.example.dressmonitor.ui.dresscode.DressCodeCheckerScreen // Commented out: File not found
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.dressmonitor.ui.attendance.AttendanceScreen
import com.example.dressmonitor.ui.auth.LoginScreen
import com.example.dressmonitor.ui.home.HomeScreen
import com.example.dressmonitor.ui.profile.ProfileScreen

// import androidx.compose.material.Text // Import if you use the Text placeholder

// Define all routes in a sealed class (safe and avoids typos)
sealed class Screen(val route: String) {
    object Login : Screen("login")
    object Home : Screen("home")
    object CameraFeed : Screen("camera_feed")
    object Attendance : Screen("attendance")
    object DressCodeChecker : Screen("dress_code_checker") // Route kept for when screen is created
    object Profile : Screen("profile")
    // You can add more here later if needed
}

@Composable
fun RootNavGraph(navController: NavHostController = rememberNavController()) {
    NavHost(
        navController = navController,
        startDestination = Screen.Login.route // First screen is Login
    ) {
        // Login Screen
        composable(Screen.Login.route) {
            LoginScreen(
                onLoginClicked = { email, password ->
                    // TODO: Implement actual login logic, e.g., call a ViewModel
                    // For now, this is a placeholder.
                    // You'll likely call a ViewModel function here, which then calls onLoginSuccess.
                },
                onLoginSuccess = {
                    // After login, go to Home
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.Login.route) { inclusive = true } // Remove Login from backstack
                    }
                },
                onNavigateToRegister = {
                    // TODO: Implement navigation to a registration screen if you have one
                    // navController.navigate("register_screen_route")
                }
            )
        }

        // Home Screen
        composable(Screen.Home.route) {
            HomeScreen(
                userName = "User", // Added placeholder userName
                onNavigate = { route -> navController.navigate(route) }
            )
        }

        @Composable
        fun CameraFeedScreen(
            onBack: () -> Unit
        ) {
            // Your UI
            // Example: Back button
            Button(onClick = onBack) {
                Text("Go Back")
            }
        }


        // Attendance Screen
        composable(Screen.Attendance.route) {
            AttendanceScreen() // Explicitly passing no parameters
        }



        // Profile Screen
        composable(Screen.Profile.route) {
            ProfileScreen() // Explicitly passing no parameters
        }
    } // <-- This closing brace for NavHost was missing its content, or there was an extra one before.
}
