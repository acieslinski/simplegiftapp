package com.amc.acieslinski.simplegiftapp.android.feature.drawingmanagement

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.BasicText
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
    val drawingState by viewModel.drawingUiState.collectAsState() // TODO lifecycle

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .wrapContentHeight(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = drawingState.getFormattedDate(),
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Text(
            text = drawingState.title,
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Text(
            text = drawingState.details,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        ParticipantsList(participants = drawingState.participants)

        Button(
            onClick = onAddParticipantClicked,
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.secondary,
                contentColor = MaterialTheme.colorScheme.onSecondary
            )
        ) {
            Text(stringResource(Res.string.drawing_participants_add))
        }

        Button(
            onClick = {
                // TODO
            },
        ) {
            Text(stringResource(Res.string.drawing_management_draw))
        }

        Button(
            onClick = {
                // TODO
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.error,
                contentColor = MaterialTheme.colorScheme.onError
            )
        ) {
            Text(stringResource(Res.string.drawing_management_close))
        }
    }
}

@Composable
fun ParticipantsList(participants: List<ParticipantUiState>) {
    Text(stringResource(Res.string.drawing_participants_title))

    LazyColumn {
        items(participants) { (name, surname) ->
            NameItem("$name $surname")
        }
    }
}

@Composable
fun NameItem(name: String) {
    Text(
        text = name,
        style = MaterialTheme.typography.bodyMedium,
        modifier = Modifier.padding(16.dp)
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