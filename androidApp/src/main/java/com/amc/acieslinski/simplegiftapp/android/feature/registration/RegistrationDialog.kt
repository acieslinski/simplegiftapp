package com.amc.acieslinski.simplegiftapp.android.feature.registration

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.amc.acieslinski.simplegiftapp.android.MyApplicationTheme
import com.amc.acieslinski.simplegiftapp.registration.presentation.RegistrationAlertState
import org.jetbrains.compose.resources.stringResource

@Composable
fun RegistrationDialog(
    alertState: RegistrationAlertState,
    onDismiss: () -> Unit,
) {
    if (alertState !is RegistrationAlertState.Hidden) {
        Dialog(
            onDismissRequest = onDismiss
        ) {
            Surface(
                modifier = Modifier
                    .width(300.dp)
                    .height(200.dp),
                color = Color.White,
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

@Preview
@Composable
fun RegistrationDialogPreview() {
    MyApplicationTheme {
        RegistrationDialog(
            alertState = RegistrationAlertState.RegistrationUnknownFailure
        ) {}
    }
}