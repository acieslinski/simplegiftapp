package com.amc.acieslinski.simplegiftapp.android.feature.drawingmanagement

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview
import com.amc.acieslinski.simplegiftapp.drawingmanagement.presentation.NewDrawingViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun NewDrawingScreen(
    viewModel: NewDrawingViewModel = getViewModel(),
    onNewDrawingDone: () -> Unit = {}
) {
    val newDrawingState by viewModel.newDrawingUiState.collectAsState() // TODO lifecycle
    val newDrawingAlertState by viewModel.newDrawingAlertState.collectAsState()

    if (newDrawingState.isSaveAck || newDrawingState.isCancelled) {
        onNewDrawingDone()
    }

    // TODO loader on saving the new drawing

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = newDrawingState.title,
            onValueChange = { viewModel.onTitleChanged(it) },
            label = { Text(text = "Title") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = newDrawingState.description,
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
            onClick = { viewModel.onCancelAction() },
            modifier = Modifier.align(Alignment.End)
        ) {
            Text(text = "Cancel")
        }
    }

    NewDrawingDialog(newDrawingAlertState) {
        viewModel.onAlertAck()
    }
}

@Preview(showBackground = true)
@Composable
fun SampleScreenPreview() {
    MaterialTheme {
        NewDrawingScreen()
    }
}