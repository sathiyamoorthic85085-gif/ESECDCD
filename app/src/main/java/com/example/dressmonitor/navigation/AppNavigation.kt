package com.example.dressmonitor.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.VerifiedUser
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(val route: String, val title: String, val icon: ImageVector) {
    object Home : Screen("home", "Home", Icons.Filled.Home)
    object Assignments : Screen("assignments", "Assignments", Icons.Filled.Description)
    object Attendance : Screen("attendance", "Attendance & Dress Code", Icons.Filled.VerifiedUser)
    object Profile : Screen("profile", "Profile", Icons.Filled.Person)
}
