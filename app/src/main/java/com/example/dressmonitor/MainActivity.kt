package com.example.dressmonitor

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Surface
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.dressmonitor.navigation.RootNavGraph
import com.example.dressmonitor.ui.theme.DressMonitorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val dressMonitorTheme = DressMonitorTheme {   // This comes from ui/theme/Theme.kt
                Surface(color = colorScheme.background) {
                    val navController: NavHostController = rememberNavController()
                    RootNavGraph(navController = navController)
                }
            }
        }
    }
}
