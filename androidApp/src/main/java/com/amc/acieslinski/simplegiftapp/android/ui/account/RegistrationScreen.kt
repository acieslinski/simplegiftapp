package com.amc.acieslinski.simplegiftapp.android.ui.account

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.amc.acieslinski.simplegiftapp.account.presentation.RegistrationViewModel
import com.amc.acieslinski.simplegiftapp.account.presentation.model.RegistrationState
import com.amc.acieslinski.simplegiftapp.android.ui.MyApplicationTheme
import com.amc.acieslinski.simplegiftapp.resources.Res
import com.amc.acieslinski.simplegiftapp.resources.account_name
import com.amc.acieslinski.simplegiftapp.resources.account_register
import com.amc.acieslinski.simplegiftapp.resources.account_surname
import org.jetbrains.compose.resources.stringResource
import org.koin.androidx.compose.getViewModel

@Composable
fun RegistrationScreen(
    viewModel: RegistrationViewModel = getViewModel(),
    onRegistrationDone: () -> Unit
) {
    val firstInputText = rememberSaveable { mutableStateOf("") }
    val secondInputText = rememberSaveable { mutableStateOf("") }
    val registrationState by viewModel.registrationState.collectAsState() // TODO lifecycle
    val registrationDialogState by viewModel.registrationDialogState.collectAsState()

    if (registrationState.isRegistrationAck) {
        onRegistrationDone()
    }

    Column {
        ContentView(
            firstInputText.value,
            { firstInputText.value = it },
            secondInputText.value,
            { secondInputText.value = it },
            registrationState,
            {
                viewModel.onRegisterAction(firstInputText.value, secondInputText.value)
            }
        )
    }

    RegistrationDialog(registrationDialogState) {
        viewModel.onNotificationAckAction()
    }
}

@Composable
private fun ContentView(
    name: String,
    onNameChanged: (name: String) -> Unit,
    surname: String,
    onSurnameChanged: (surname: String) -> Unit,
    registrationState: RegistrationState,
    onRegistrationAction: () -> Unit
) {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .wrapContentHeight(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Column {
            Text(text = stringResource(Res.string.account_name), style = MaterialTheme.typography.labelMedium)
            Spacer(modifier = Modifier.height(16.dp))
            TextField(
                value = name,
                onValueChange = onNameChanged,
                modifier = Modifier.fillMaxWidth()
            )
        }

        Column {
            Text(text = stringResource(Res.string.account_surname), style = MaterialTheme.typography.labelMedium)
            Spacer(modifier = Modifier.height(16.dp))
            TextField(
                value = surname,
                onValueChange = onSurnameChanged,
                modifier = Modifier.fillMaxWidth()
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
        ) {
            if (registrationState.isLoading) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }

            Button(
                onClick = onRegistrationAction,
                modifier = Modifier.align(Alignment.Center),
                enabled = !registrationState.isLoading
            ) {
                Text(stringResource(Res.string.account_register))
            }
        }
    }
}

@Preview
@Composable
fun RegisterScreenPreview() {
    MyApplicationTheme {
        RegistrationScreen {}
    }
}