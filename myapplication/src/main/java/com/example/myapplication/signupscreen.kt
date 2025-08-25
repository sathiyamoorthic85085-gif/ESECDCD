package com.example.dressmonitor.ui.screens // Adjust your package

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.password
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.myapplication.navigation.AuthScreen // Your AuthScreen routes
import com.example.myapplication.navigation.Graph      // Your Graph object
import com.example.myapplication.ui.auth.AuthViewModel // Your AuthViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpScreen(
    navController: NavHostController,
    authViewModel: AuthViewModel
) {
    val context = LocalContext.current
    // In SignUp, loginSuccessful is also set to true on success for auto-login
    val signUpSuccessfulAndLoggedIn by authViewModel.loginSuccessful.observeAsState()
    val errorMessage by authViewModel.errorMessage.observeAsState()

    // Navigate on successful sign-up (which also logs in)
    LaunchedEffect(signUpSuccessfulAndLoggedIn) {
        if (signUpSuccessfulAndLoggedIn == true) {
            navController.navigate(Graph.MAIN_APP) {
                popUpTo(Graph.AUTHENTICATION) { inclusive = true }
                launchSingleTop = true
            }
            authViewModel.resetActionStates() // Reset flag in ViewModel
        }
    }

    // Show error messages
    LaunchedEffect(errorMessage) {
        errorMessage?.let {
            Toast.makeText(context, it, Toast.LENGTH_LONG).show()
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
        Text("Sign Up Screen", style = MaterialTheme.typography.headlineLarge)
        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            value = authViewModel.email.value,
            onValueChange = { authViewModel.email.value = it },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = authViewModel.password.value,
            onValueChange = { authViewModel.password.value = it },
            label = { Text("Password") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { authViewModel.performSignUp() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Sign Up")
        }
        TextButton(
            onClick = { navController.navigate(AuthScreen.Login.route) { popUpTo(AuthScreen.Login.route){inclusive = true} } } // Go back to login
        ) {
            Text("Already have an account? Login")
        }
    }
}
