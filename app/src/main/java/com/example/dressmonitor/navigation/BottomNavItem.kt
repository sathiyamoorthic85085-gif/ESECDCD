package com.example.dressmonitor.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector

private val Icons.Filled.VerifiedUser: ImageVector
    get() {
        TODO()
    }
private val Icons.Filled.Assignment: ImageVector
    get() {
        TODO()
    }

sealed class BottomNavItem(val route: String, val title: String, val icon: ImageVector) {
    object Home : BottomNavItem("home", "Home", Icons.Filled.Home)
    object Assignments : BottomNavItem("assignments", "Assignments", Icons.Filled.Assignment)
    object Attendance : BottomNavItem("attendance", "Attendance", Icons.Filled.VerifiedUser)
    object Profile : BottomNavItem("profile", "Profile", Icons.Filled.Person)
}
