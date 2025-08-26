package com.example.dressmonitor.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.dressmonito.viewmodels.CameraViewModel

@Composable
fun CameraFeedScreen(cameraViewModel: CameraViewModel = viewModel()) {
    val imageUrl by cameraViewModel.imageUrl.collectAsState()
    val isLoading by cameraViewModel.isLoading.collectAsState()
    val errorMessage by cameraViewModel.errorMessage.collectAsState()
    @Composable
    fun CameraFeedScreen(onBack: () -> Unit) {
        Column {
            Button(onClick = { onBack() }) {
                Text("Back")
            }

            // TODO: Your camera preview/feed UI here
            Text("Camera Feed Screen")
        }
    }


    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        when {
            isLoading -> {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    CircularProgressIndicator()
                    Spacer(modifier = Modifier.height(12.dp))
                    Text("Loading camera feed...")
                }
            }

            errorMessage != null -> {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text("Error: $errorMessage", color = MaterialTheme.colorScheme.error)
                    Spacer(modifier = Modifier.height(8.dp))
                    Button(onClick = { cameraViewModel.fetchImage() }) {
                        Text("Retry")
                    }
                }
            }

            imageUrl != null -> {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(imageUrl)
                        .crossfade(true)
                        .build(),
                    contentDescription = "Raspberry Pi Camera Feed",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Fit
                )
            }

            else -> {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text("Camera feed not available. Tap refresh.")
                    Spacer(modifier = Modifier.height(8.dp))
                    Button(onClick = { cameraViewModel.fetchImage() }) {
                        Text("Refresh")
                    }
                }
            }
        }
    }
}
