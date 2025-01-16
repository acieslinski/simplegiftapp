package com.amc.acieslinski.simplegiftapp.drawingmanagement.presentation

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.datetime.Clock

class DrawingFakeViewModel : DrawingViewModel() {
    override val drawingUiState: StateFlow<DrawingUiState> = MutableStateFlow(
        DrawingUiState(
            title = "Preview title",
            details = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque vel nisi id nulla feugiat fermentum non sed velit.",
            date = Clock.System.now(),
            participants = listOf(
                ParticipantUiState(
                    "name", "surname", "id"
                )
            ),
            drawnParticipant = ParticipantUiState(
                "name", "surname", "id"
            )
        )
    )

    override fun onCloseDrawingAction() {
        TODO("Not yet implemented")
    }

    override fun onDrawParticipantAction() {
        TODO("Not yet implemented")
    }

    override fun onAlertAckAction() {
        TODO("Not yet implemented")
    }
}