package com.amc.acieslinski.simplegiftapp.android.feature.drawingmanagement

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.amc.acieslinski.simplegiftapp.drawingmanagement.presentation.NewDrawingViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun NewDrawingScreen(
    viewModel: NewDrawingViewModel = getViewModel(),
    onNewDrawingDismissed: () -> Unit = {}
) {
    val state by viewModel.newDrawingUiState.collectAsStateWithLifecycle()
    val alertState by viewModel.newDrawingAlertState.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = state.title,
            onValueChange = { viewModel.onTitleChanged(it) },
            label = { Text(text = "Title") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = state.description,
            onValueChange = { viewModel.onDescriptionChanged(it) },
            label = { Text(text = "Description") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(24.dp))
        Button(
            onClick = { viewModel.onCreateDrawingAction() },
            modifier = Modifier.align(Alignment.End)
        ) {
            Text(text = "Create")
        }
        Button(
            onClick = onNewDrawingDismissed,
            modifier = Modifier.align(Alignment.End)
        ) {
            Text(text = "Cancel")
        }
    }

    if (state.isSaving) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background.copy(alpha = 0.7f)),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    }

    NewDrawingDialog(alertState) {
        viewModel.onAlertAckAction()
        onNewDrawingDismissed()
    }
}

@Preview(showBackground = true)
@Composable
fun SampleScreenPreview() {
    MaterialTheme {
        NewDrawingScreen()
    }
}