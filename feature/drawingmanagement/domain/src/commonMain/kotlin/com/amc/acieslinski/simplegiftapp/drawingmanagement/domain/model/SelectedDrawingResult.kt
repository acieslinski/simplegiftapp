package com.amc.acieslinski.simplegiftapp.drawingmanagement.domain.model

sealed class SelectedDrawingResult {
    data object Empty : SelectedDrawingResult()

    data class Success(val drawing: Drawing) : SelectedDrawingResult()
}