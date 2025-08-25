import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.dressmonitor.ui.theme.DressMonitorTheme

// Main Activity
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DressMonitorTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    DressMonitorApp()
                }
            }
        }
    }
}

// Navigation routes
object AuthScreen {
    const val Login = "login"
    const val SignUp = "signup"
}

object Graph {
    const val AUTHENTICATION = "auth"
    const val MAIN_APP = "main"
}

// Auth ViewModel
class AuthViewModel : ViewModel() {
    val email = mutableStateOf("")
    val password = mutableStateOf("")
    val loginSuccessful = mutableStateOf<Boolean?>(null)
    val errorMessage = mutableStateOf<String?>(null)
    val isLoading = mutableStateOf(false)

    fun performLogin() {
        isLoading.value = true
        // Simulate network request
        android.os.Handler().postDelayed({
            isLoading.value = false
            if (email.value.isNotBlank() && password.value.length >= 6) {
                loginSuccessful.value = true
                errorMessage.value = null
            } else {
                loginSuccessful.value = false
                errorMessage.value = "Invalid credentials. Please try again."
            }
        }, 1500)
    }

    fun resetActionStates() {
        loginSuccessful.value = null
    }

    fun clearErrorMessage() {
        errorMessage.value = null
    }
}

// Login Screen
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    navController: NavHostController,
    authViewModel: AuthViewModel = viewModel()
) {
    val context = LocalContext.current
    val loginSuccessful by authViewModel.loginSuccessful
    val errorMessage by authViewModel.errorMessage
    val isLoading by authViewModel.isLoading

    // Navigate on successful login
    LaunchedEffect(loginSuccessful) {
        if (loginSuccessful == true) {
            // In a real app, you would navigate to the main app screen
            // navController.navigate(Graph.MAIN_APP) {
            //    popUpTo(Graph.AUTHENTICATION) { inclusive = true }
            //    launchSingleTop = true
            // }
            // For demo purposes, we'll just show a message
            android.widget.Toast.makeText(context, "Login successful!", android.widget.Toast.LENGTH_SHORT).show()
            authViewModel.resetActionStates()
        }
    }

    // Show error messages
    LaunchedEffect(errorMessage) {
        errorMessage?.let {
            android.widget.Toast.makeText(context, it, android.widget.Toast.LENGTH_LONG).show()
            authViewModel.clearErrorMessage()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Welcome Back", style = MaterialTheme.typography.headlineLarge)
        Text("Sign in to continue", style = MaterialTheme.typography.bodyMedium)
        Spacer(modifier = Modifier.height(32.dp))

        OutlinedTextField(
            value = authViewModel.email.value,
            onValueChange = { authViewModel.email.value = it },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            enabled = !isLoading
        )
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = authViewModel.password.value,
            onValueChange = { authViewModel.password.value = it },
            label = { Text("Password") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            enabled = !isLoading
        )
        Spacer(modifier = Modifier.height(24.dp))

        val isLoginButtonEnabled = authViewModel.email.value.isNotBlank() &&
                authViewModel.password.value.isNotBlank() &&
                !isLoading

        Button(
            onClick = { authViewModel.performLogin() },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            enabled = isLoginButtonEnabled
        ) {
            if (isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.size(24.dp),
                    color = MaterialTheme.colorScheme.onPrimary
                )
            } else {
                Text("Login", style = MaterialTheme.typography.labelLarge)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        TextButton(
            onClick = { /* navController.navigate(AuthScreen.SignUp.route) */ },
            enabled = !isLoading
        ) {
            Text("No account? Sign Up")
        }
    }
}

// Main App
@Composable
fun DressMonitorApp() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = AuthScreen.Login
    ) {
        composable(AuthScreen.Login) {
            LoginScreen(navController = navController)
        }
        // Add other destinations here
    }
}

// Preview
@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    DressMonitorTheme {
        LoginScreen(navController = rememberNavController())
    }
}