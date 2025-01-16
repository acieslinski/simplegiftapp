package com.amc.acieslinski.simplegiftapp.android.feature.drawingmanagement

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.amc.acieslinski.simplegiftapp.android.MyApplicationTheme
import com.amc.acieslinski.simplegiftapp.drawingmanagement.presentation.DrawingViewModel
import com.amc.acieslinski.simplegiftapp.drawingmanagement.presentation.DrawingFakeViewModel
import com.amc.acieslinski.simplegiftapp.drawingmanagement.presentation.ParticipantUiState
import com.amc.acieslinski.simplegiftapp.resources.Res
import com.amc.acieslinski.simplegiftapp.resources.drawing_management_close
import com.amc.acieslinski.simplegiftapp.resources.drawing_management_draw
import com.amc.acieslinski.simplegiftapp.resources.drawing_participants_add
import com.amc.acieslinski.simplegiftapp.resources.drawing_participants_title
import org.jetbrains.compose.resources.stringResource
import org.koin.androidx.compose.getViewModel

@Composable
fun DrawingScreen(
    viewModel: DrawingViewModel = getViewModel(),
    onAddParticipantClicked: () -> Unit,
) {
    val drawingState by viewModel.drawingUiState.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .wrapContentHeight(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Display Date
        Text(
            text = drawingState.getFormattedDate(),
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        // Title
        Text(
            text = drawingState.title,
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        // Details
        Text(
            text = drawingState.details,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Participant List
        ParticipantsList(participants = drawingState.participants)

        // Add Participant Button
        Button(
            onClick = onAddParticipantClicked,
            enabled = drawingState.isAddingParticipantAvailable,
            shape = MaterialTheme.shapes.medium,
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.secondary,
                contentColor = MaterialTheme.colorScheme.onSecondary
            ),
            modifier = Modifier.fillMaxWidth(0.8f)
        ) {
            Text(stringResource(Res.string.drawing_participants_add))
        }

        // Draw Participant Button
        Button(
            onClick = viewModel::onDrawParticipantAction,
            enabled = drawingState.isDrawingAvailable,
            shape = MaterialTheme.shapes.medium,
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary
            ),
            modifier = Modifier.fillMaxWidth(0.8f)
        ) {
            Text(stringResource(Res.string.drawing_management_draw))
        }

        // Display Drawn Participant (if available)
        drawingState.drawnParticipant?.let { with(it) {
            Text(
                text = "$name $surname",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.padding(top = 16.dp, bottom = 16.dp)
            )
        } }

        // Close Drawing Button
        Button(
            onClick = viewModel::onCloseDrawingAction,
            enabled = drawingState.isCloseDrawingAvailable,
            shape = MaterialTheme.shapes.medium,
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.error,
                contentColor = MaterialTheme.colorScheme.onError
            ),
            modifier = Modifier.fillMaxWidth(0.8f)
        ) {
            Text(stringResource(Res.string.drawing_management_close))
        }
    }

    // Drawing Dialog
    DrawingDialog(alertState = drawingState.drawingAlertState) {
        viewModel.onAlertAckAction()
    }
}

@Composable
fun ParticipantsList(participants: List<ParticipantUiState>) {
    Column(modifier = Modifier.fillMaxWidth()) {
        // Participants List Title
        Text(
            text = stringResource(Res.string.drawing_participants_title),
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(8.dp)
        )

        // Participants Items
        LazyColumn {
            items(participants) { (name, surname) ->
                NameItem("$name $surname")
            }
        }
    }
}

@Composable
fun NameItem(name: String) {
    Text(
        text = name,
        style = MaterialTheme.typography.bodyMedium,
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
        color = MaterialTheme.colorScheme.onSurface
    )
}

@Preview
@Composable
fun DrawingScreenPreview() {
    MyApplicationTheme {
        DrawingScreen(
            viewModel = DrawingFakeViewModel(),
            onAddParticipantClicked = {},
        )
    }
}
