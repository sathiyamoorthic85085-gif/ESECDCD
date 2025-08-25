package com.example.dressmonitor.ui.screens // Adjust your package

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.myapplication.navigation.Graph // Your Graph object
import com.example.myapplication.ui.auth.AuthViewModel // Your AuthViewModel
// import kotlinx.coroutines.delay // Optional: for minimum splash time

@Composable
fun SplashScreen(
    navController: NavHostController,
    authViewModel: AuthViewModel
) {
    val isAuthenticated by authViewModel.isAuthenticated.observeAsState()

    LaunchedEffect(key1 = isAuthenticated) {
        // Optional: delay(1500L) // Minimum splash display time

        when (isAuthenticated) {
            true -> { // Authenticated
                navController.navigate(Graph.MAIN_APP) {
                    popUpTo(Graph.SPLASH) { inclusive = true }
                    launchSingleTop = true
                }
            }
            false -> { // Not Authenticated
                navController.navigate(Graph.AUTHENTICATION) {
                    popUpTo(Graph.SPLASH) { inclusive = true }
                    launchSingleTop = true
                }
            }
            null -> { /* Still loading, do nothing */ }
        }
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
        Text("Loading Splash...")
    }
}
