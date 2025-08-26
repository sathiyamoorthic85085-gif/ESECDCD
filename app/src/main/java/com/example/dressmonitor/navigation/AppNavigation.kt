
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.VerifiedUser
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector

@Composable
fun CameraFeasScreen(
    onBack: () -> Unit // Renamed to match usage
) {
    Button(onClick = onBack) {
        Text("Go Back")
    }
}

// Corrected sealed class
sealed class Screen(val route: String, val title: String, val icon: ImageVector) {
    object Home : Screen("home", "Home", Icons.Filled.Home)
    object Assignments : Screen("assignments", "Assignments", Icons.Filled.Description)
    object Attendance : Screen("attendance", "Attendance & Dress Code", Icons.Filled.VerifiedUser)
}