package com.amc.acieslinski.simplegiftapp.android.feature.drawingmanagement

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.amc.acieslinski.simplegiftapp.android.components.qrscanner.ScannerView
import com.amc.acieslinski.simplegiftapp.drawingmanagement.presentation.ParticipantQrScannerFakeViewModel
import com.amc.acieslinski.simplegiftapp.drawingmanagement.presentation.ParticipantQrScannerViewModel
import com.amc.acieslinski.simplegiftapp.resources.Res
import com.amc.acieslinski.simplegiftapp.resources.scanner_denied
import com.amc.acieslinski.simplegiftapp.resources.scanner_hint_scan
import com.amc.acieslinski.simplegiftapp.resources.scanner_permission_request
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.permissions.shouldShowRationale
import org.jetbrains.compose.resources.stringResource
import org.koin.androidx.compose.getViewModel

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun ParticipantQrScannerScreen(
    viewModel: ParticipantQrScannerViewModel = getViewModel(),
    onDismiss: () -> Unit,
) {
    val isDismissed = remember { mutableStateOf(false) } // to resolve issue with not disappearing scanner box on navigating back
    val state by viewModel.participantQrScannerUiState.collectAsState() // TODO lifecycle
    val cameraPermissionState = rememberPermissionState(android.Manifest.permission.CAMERA)

    BackHandler {
        isDismissed.value = true
        onDismiss()
    }

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

    if (!isDismissed.value && state.alertState.isHidden) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp)
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxSize(),
            ) {
                Text(
                    text = message,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                )

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1f)
                        .padding(40.dp)
                        .background(Color.Black)
                ) {
                    if (cameraPermissionState.status.isGranted) {
                        ScannerView {
                            viewModel.onAddParticipantAction(it)
                        }
                    }
                }
            }
        }
    }

    if (state.isAdding) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background.copy(alpha = 0.7f)),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    }

    ParticipantQrScannerDialog(state.alertState) {
        viewModel.onAlertAckAction()
        isDismissed.value = true
        onDismiss()
    }
}

@Preview(showBackground = true)
@Composable
fun ParticipantQrScannerScreenPreview() {
    MaterialTheme {
        ParticipantQrScannerScreen(
            viewModel = ParticipantQrScannerFakeViewModel(),
            onDismiss = {}
        )
    }
}