package com.amc.acieslinski.simplegiftapp.drawingmanagement.domain.model

sealed interface SelectedDrawingUpdate {
    data class Success(
        val drawing: Drawing
    ): SelectedDrawingUpdate

    sealed interface Failure: SelectedDrawingUpdate

    data object UnknownFailure: Failure
}