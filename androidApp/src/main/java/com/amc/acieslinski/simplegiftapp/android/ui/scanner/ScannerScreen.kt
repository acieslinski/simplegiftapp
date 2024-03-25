package com.amc.acieslinski.simplegiftapp.android.ui.scanner

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.permissions.shouldShowRationale

@ExperimentalPermissionsApi
@Composable
fun ScannerScreen(onCodeScanned: (String) -> Unit) {
    val cameraPermissionState = rememberPermissionState(android.Manifest.permission.CAMERA)

    val message = if (cameraPermissionState.status.isGranted) {
        "Scan your friend's QR code to add them to your participant list"
    } else if (cameraPermissionState.status.shouldShowRationale) {
        "Camera Permission permanently denied"
    } else {
        SideEffect {
            cameraPermissionState.run { launchPermissionRequest() }
        }
        "No Camera Permission"
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            Text(
                text = message,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp) // Padding for the header text
            )

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(40.dp)
                    .background(Color.Black)
            ) {
                if (cameraPermissionState.status.isGranted) {
                    CameraScreen(onCodeScanned)
                }
            }
        }
    }
}