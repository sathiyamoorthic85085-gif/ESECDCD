package com.example.dressmonitor.ui.auth

import android.util.Log
import android.util.Patterns
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
class AuthViewModel : ViewModel() {

    // Compose UI states
    val email = mutableStateOf("")
    val password = mutableStateOf("")

    // LiveData for authentication status and errors
    private val _loginSuccessful = MutableLiveData<Boolean>()
    val loginSuccessful: LiveData<Boolean> get() = _loginSuccessful

    private val _signUpSuccessful = MutableLiveData<Boolean>()
    val signUpSuccessful: LiveData<Boolean> get() = _signUpSuccessful

    private val _passwordResetSent = MutableLiveData<Boolean>()
    val passwordResetSent: LiveData<Boolean> get() = _passwordResetSent

    private val _isAuthenticated = MutableLiveData<Boolean?>(null)
    val isAuthenticated: LiveData<Boolean?> get() = _isAuthenticated

    private val _errorMessage = MutableLiveData<String?>()
    val errorMessage: LiveData<String?> get() = _errorMessage

    private val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()

    init {
        checkUserAuthenticationStatus()
    }

    private fun checkUserAuthenticationStatus() {
        val currentUser: FirebaseUser? = firebaseAuth.currentUser
        _isAuthenticated.value = currentUser != null
        Log.d("AuthViewModel", if (currentUser != null) "User is authenticated" else "User is not authenticated")
    }

    private fun isEmailValid(email: String) = Patterns.EMAIL_ADDRESS.matcher(email).matches()

    fun performLogin() {
        val currentEmail = email.value.trim()
        val currentPassword = password.value

        if (currentEmail.isBlank() || currentPassword.isBlank()) {
            _errorMessage.value = "Email and password cannot be blank."
            _loginSuccessful.value = false
            return
        }
        if (!isEmailValid(currentEmail)) {
            _errorMessage.value = "Invalid email format."
            _loginSuccessful.value = false
            return
        }

        viewModelScope.launch {
            try {
                firebaseAuth.signInWithEmailAndPassword(currentEmail, currentPassword).await()
                _loginSuccessful.value = true
                _isAuthenticated.value = true
                _errorMessage.value = null
                Log.d("AuthViewModel", "Login successful for $currentEmail")
            } catch (e: Exception) {
                _loginSuccessful.value = false
                _isAuthenticated.value = false
                _errorMessage.value = e.localizedMessage ?: "Login failed"
                Log.e("AuthViewModel", "Login failed for $currentEmail", e)
            }
        }
    }

    fun performSignUp() {
        val currentEmail = email.value.trim()
        val currentPassword = password.value

        if (currentEmail.isBlank() || currentPassword.isBlank()) {
            _errorMessage.value = "Email and password cannot be blank."
            _signUpSuccessful.value = false
            return
        }
        if (!isEmailValid(currentEmail)) {
            _errorMessage.value = "Invalid email format."
            _signUpSuccessful.value = false
            return
        }
        if (currentPassword.length < 6) {
            _errorMessage.value = "Password must be at least 6 characters."
            _signUpSuccessful.value = false
            return
        }

        viewModelScope.launch {
            try {
                firebaseAuth.createUserWithEmailAndPassword(currentEmail, currentPassword).await()
                _signUpSuccessful.value = true
                _loginSuccessful.value = true // Auto-login
                _isAuthenticated.value = true
                _errorMessage.value = null
                Log.d("AuthViewModel", "SignUp successful for $currentEmail")
            } catch (e: Exception) {
                _signUpSuccessful.value = false
                _isAuthenticated.value = false
                _errorMessage.value = e.localizedMessage ?: "Sign up failed"
                Log.e("AuthViewModel", "SignUp failed for $currentEmail", e)
            }
        }
    }

    fun performPasswordReset() {
        val currentEmail = email.value.trim()
        if (currentEmail.isBlank()) {
            _errorMessage.value = "Email cannot be blank."
            _passwordResetSent.value = false
            return
        }
        if (!isEmailValid(currentEmail)) {
            _errorMessage.value = "Invalid email format."
            _passwordResetSent.value = false
            return
        }

        viewModelScope.launch {
            try {
                firebaseAuth.sendPasswordResetEmail(currentEmail).await()
                _passwordResetSent.value = true
                _errorMessage.value = null
                Log.d("AuthViewModel", "Password reset email sent to $currentEmail")
            } catch (e: Exception) {
                _passwordResetSent.value = false
                _errorMessage.value = e.localizedMessage ?: "Failed to send password reset email"
                Log.e("AuthViewModel", "Password reset failed for $currentEmail", e)
            }
        }
    }

    fun performLogout() {
        firebaseAuth.signOut()
        _isAuthenticated.value = false
        email.value = ""
        password.value = ""
        _loginSuccessful.value = false
        _signUpSuccessful.value = false
        _passwordResetSent.value = false
        _errorMessage.value = null
        Log.d("AuthViewModel", "User logged out")
    }

    fun resetActionStates() {
        _loginSuccessful.value = false
        _signUpSuccessful.value = false
        _passwordResetSent.value = false
        _errorMessage.value = null
    }

    fun clearErrorMessage() {
        _errorMessage.value = null
    }
}
