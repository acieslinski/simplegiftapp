package com.amc.acieslinski.simplegiftapp.android.ui.drawing

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.BasicText
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.amc.acieslinski.simplegiftapp.android.ui.MyApplicationTheme
import com.amc.acieslinski.simplegiftapp.android.ui.navigation.ScannerNav
import com.amc.acieslinski.simplegiftapp.drawing.presentation.DrawingUiState
import com.amc.acieslinski.simplegiftapp.drawing.presentation.DrawingViewModel
import com.amc.acieslinski.simplegiftapp.drawing.presentation.ParticipantUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import org.koin.androidx.compose.getViewModel

@Composable
fun DrawingScreen(
    navScanner: ScannerNav,
    viewModel: DrawingViewModel = getViewModel()
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
        ParticipantsList(participants = drawingState.participants)

        Button(
            onClick = {
                navScanner.startScannerScreen {
                    viewModel.addParticipant(it)
                }
            },
        ) {
            Text("Add participant")
        }
    }
}

@Composable
fun ParticipantsList(participants: List<ParticipantUiState>) {
    Text("Participants list")

    LazyColumn {
        items(participants) { (name, surname) ->
            NameItem("$name $surname")
        }
    }
}

@Composable
fun NameItem(name: String) {
    BasicText(text = name, modifier = Modifier.padding(16.dp))
}

@Preview
@Composable
fun DrawingScreenPreview() {
    MyApplicationTheme {
        DrawingScreen(ScannerNav.DUMB, object : DrawingViewModel() {
            override val drawingUiState: StateFlow<DrawingUiState> = MutableStateFlow(
                DrawingUiState(
                    participants = listOf(ParticipantUiState(
                        "name", "surname", "id"
                    ))
                )
            )

            override fun addParticipant(id: String) {
                error("not supported")
            }
        })
    }
}