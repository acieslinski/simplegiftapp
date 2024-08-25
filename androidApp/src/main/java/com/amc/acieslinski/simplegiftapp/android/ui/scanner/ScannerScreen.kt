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
import com.amc.acieslinski.simplegiftapp.resources.Res
import com.amc.acieslinski.simplegiftapp.resources.scanner_denied
import com.amc.acieslinski.simplegiftapp.resources.scanner_hint_scan
import com.amc.acieslinski.simplegiftapp.resources.scanner_permission_request
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.permissions.shouldShowRationale
import org.jetbrains.compose.resources.stringResource

@ExperimentalPermissionsApi
@Composable
fun ScannerScreen(onCodeScanned: (String) -> Unit) {
    val cameraPermissionState = rememberPermissionState(android.Manifest.permission.CAMERA)

    val message = if (cameraPermissionState.status.isGranted) {
        stringResource(Res.string.scanner_hint_scan)
    } else if (cameraPermissionState.status.shouldShowRationale) {
        stringResource(Res.string.scanner_denied)
    } else {
        SideEffect {
            cameraPermissionState.run { launchPermissionRequest() }
        }
        stringResource(Res.string.scanner_permission_request)
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