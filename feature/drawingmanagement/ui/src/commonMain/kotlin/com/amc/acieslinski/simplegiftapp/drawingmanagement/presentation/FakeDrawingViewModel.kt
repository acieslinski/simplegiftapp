package com.amc.acieslinski.simplegiftapp.drawingmanagement.presentation

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.datetime.Clock

class FakeDrawingViewModel : DrawingViewModel() {
    override val drawingUiState: StateFlow<DrawingUiState> = MutableStateFlow(
        DrawingUiState(
            title = "Preview title",
            details = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque vel nisi id nulla feugiat fermentum non sed velit.",
            date = Clock.System.now(),
            participants = listOf(
                ParticipantUiState(
                    "name", "surname", "id"
                )
            )
        )
    )

    override fun addParticipant(id: String) {
        error("not supported")
    }
}