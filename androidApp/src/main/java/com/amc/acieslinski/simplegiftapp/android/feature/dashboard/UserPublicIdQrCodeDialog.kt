package com.amc.acieslinski.simplegiftapp.android.feature.dashboard

import android.graphics.BitmapFactory
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.amc.acieslinski.simplegiftapp.dashboard.presentation.UserPublicIdQrCodeUiState
import org.jetbrains.compose.resources.stringResource

@Composable
fun UserPublicIdQrCodeDialog(
    dialogState: UserPublicIdQrCodeUiState,
    onDismiss: () -> Unit,
) {
    BackHandler {
        onDismiss()
    }

    if (dialogState.isVisible) {
        Dialog(
            onDismissRequest = onDismiss
        ) {
            Surface(
                modifier = Modifier
                    .width(300.dp)
                    .height(400.dp),
                color = MaterialTheme.colorScheme.surface,
                shadowElevation = 8.dp,
                shape = MaterialTheme.shapes.large
            ) {
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    if (dialogState.isLoading) {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(16.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator()
                        }
                    } else {
                        Text(text = stringResource(dialogState.hintRes))
                        Spacer(modifier = Modifier.height(16.dp))
                        QRCode(pngBytes = dialogState.qrCode)
                    }
                }
            }
        }
    }
}

@Composable
fun QRCode(pngBytes: ByteArray) {
    val bitmap = BitmapFactory.decodeByteArray(pngBytes, 0, pngBytes.size)

    val imageBitmap = bitmap.asImageBitmap()

    Image(
        bitmap = imageBitmap,
        contentDescription = "QR Code",
        modifier = Modifier
            .padding(25.dp)
            .fillMaxWidth()
            .aspectRatio(1f)
    )
}