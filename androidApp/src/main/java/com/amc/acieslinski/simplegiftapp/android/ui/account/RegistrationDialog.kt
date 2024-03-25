package com.amc.acieslinski.simplegiftapp.android.ui.account

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
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.amc.acieslinski.simplegiftapp.account.presentation.model.RegistrationDialogState
import com.amc.acieslinski.simplegiftapp.android.ui.account.mapper.RegistrationDialogStateMapper

@Composable
fun RegistrationDialog(
    registrationNotification: RegistrationDialogState,
    onDismiss: () -> Unit,
) {
    val uiRegistrationNotification = remember(key1 = registrationNotification) {
        RegistrationDialogStateMapper.INSTANCE.mapToUi(registrationNotification)
    }

    if (uiRegistrationNotification != null) {
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
                    Text(text = stringResource(id = uiRegistrationNotification.messageId))
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(onClick = {
                        onDismiss()
                    }) {
                        Text(text = "Close")
                    }
                }
            }
        }
    }
}