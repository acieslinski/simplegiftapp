package com.amc.acieslinski.simplegiftapp.android.feature.drawingmanagement

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.amc.acieslinski.simplegiftapp.drawingmanagement.presentation.DrawingAlertState
import com.amc.acieslinski.simplegiftapp.drawingmanagement.presentation.NewDrawingAlertState
import com.amc.acieslinski.simplegiftapp.drawingmanagement.presentation.ParticipantQrScannerAlertState
import org.jetbrains.compose.resources.stringResource

@Composable
fun DrawingDialog(
    alertState: DrawingAlertState,
    onDismiss: () -> Unit,
) {
    if (alertState is DrawingAlertState.Visible) {
        Dialog(
            onDismissRequest = onDismiss
        ) {
            Surface(
                modifier = Modifier
                    .width(300.dp)
                    .height(200.dp),
                shadowElevation = 8.dp,
                color = MaterialTheme.colorScheme.surface,
                shape = MaterialTheme.shapes.large,
            ) {
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = stringResource(alertState.messageRes))
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(onClick = {
                        onDismiss()
                    }) {
                        Text(text = stringResource(alertState.closeLabelRes))
                    }
                }
            }
        }
    }
}