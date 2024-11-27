package com.amc.acieslinski.simplegiftapp.drawingmanagement.presentation.model

sealed interface NewDrawingAlertState {
    data object Confirmation : NewDrawingAlertState

    data class SaveError(val error: SaveDrawingError) : NewDrawingAlertState

    data object Hidden : NewDrawingAlertState
}