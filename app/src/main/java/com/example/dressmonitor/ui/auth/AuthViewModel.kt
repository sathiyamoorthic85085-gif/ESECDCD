package com.example.dressmonitor.ui.auth

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dressmonito.data.UserRole
import com.google.firebase.Timestamp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class AuthViewModel : ViewModel() {

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    private val db: FirebaseFirestore = FirebaseFirestore.getInstance()

    // Holds the current FirebaseUser object
    private val _currentUser = MutableStateFlow<FirebaseUser?>(null)
    val currentUser: StateFlow<FirebaseUser?> = _currentUser

    // Holds the current authentication screen state
    private val _authScreenState = MutableStateFlow<AuthScreenState>(AuthScreenState.Unauthenticated)
    val authScreenState: StateFlow<AuthScreenState> = _authScreenState

    // Holds the custom role of the authenticated user
    private val _currentUserRole = MutableStateFlow<UserRole?>(null)
    val currentUserRole: StateFlow<UserRole?> = _currentUserRole

    init {
        auth.addAuthStateListener { firebaseAuth ->
            val user = firebaseAuth.currentUser
            _currentUser.value = user
            if (user != null) {
                _authScreenState.value = AuthScreenState.Authenticated(user)
                fetchUserRoleAndDetails(user.uid)
            } else {
                _authScreenState.value = AuthScreenState.Unauthenticated
                _currentUserRole.value = null
            }
        }
    }

    fun loginUser(email: String, password: String) {
        viewModelScope.launch {
            _authScreenState.value = AuthScreenState.Loading
            try {
                auth.signInWithEmailAndPassword(email, password).await()
                // AuthStateListener will update state
            } catch (e: Exception) {
                _authScreenState.value = AuthScreenState.Error(e.message ?: "Login failed")
            }
        }
    }

    fun registerUser(email: String, password: String, role: UserRole) {   // ✅ fixed type
        viewModelScope.launch {
            _authScreenState.value = AuthScreenState.Loading
            try {
                val authResult = auth.createUserWithEmailAndPassword(email, password).await()
                if (authResult.user != null) {
                    storeUserRole(authResult.user!!.uid, role, email)
                } else {
                    _authScreenState.value = AuthScreenState.Error("User creation failed: no user object.")
                }
            } catch (e: Exception) {
                _authScreenState.value = AuthScreenState.Error(e.message ?: "Registration failed")
            }
        }
    }

    private fun storeUserRole(userId: String, role: UserRole, email: String) {  // ✅ fixed type
        val userDetails = hashMapOf(
            "email" to email,
            "role" to role.name,
            "createdAt" to Timestamp.now()
        )

        db.collection("users").document(userId).set(userDetails)
            .addOnSuccessListener {
                Log.d("AuthViewModel", "User role stored successfully for UID: $userId")
            }
            .addOnFailureListener { e ->
                Log.w("AuthViewModel", "Error storing user role for UID: $userId", e)
            }
    }

    private fun fetchUserRoleAndDetails(userId: String) {
        viewModelScope.launch {
            try {
                val documentSnapshot = db.collection("users").document(userId).get().await()
                if (documentSnapshot.exists()) {
                    val roleString = documentSnapshot.getString("role")
                    _currentUserRole.value = try {
                        roleString?.let { UserRole.valueOf(it) }
                    } catch (e: IllegalArgumentException) {
                        Log.e("AuthViewModel", "Invalid role string in Firestore: $roleString")
                        null
                    }
                    Log.d("AuthViewModel", "Role fetched: ${_currentUserRole.value}")
                } else {
                    _currentUserRole.value = null
                    Log.w("AuthViewModel", "User document not found in Firestore (UID: $userId)")
                }
            } catch (e: Exception) {
                _currentUserRole.value = null
                Log.e("AuthViewModel", "Error fetching role (UID: $userId)", e)
            }
        }
    }

    fun logout() {
        auth.signOut()
        // AuthStateListener will reset state
    }
}

// Authentication states
sealed class AuthScreenState {
    object Unauthenticated : AuthScreenState()
    object Loading : AuthScreenState()
    data class Authenticated(val user: FirebaseUser) : AuthScreenState()
    data class Error(val message: String) : AuthScreenState()
}
