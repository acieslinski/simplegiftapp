package com.amc.acieslinski.simplegiftapp.drawingmanagement.domain.model

sealed interface SelectedDrawingIdResult {
    data class Success(val id: String): SelectedDrawingIdResult
    data object Empty: SelectedDrawingIdResult
}