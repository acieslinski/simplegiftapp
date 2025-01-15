package com.amc.acieslinski.simplegiftapp.drawingmanagement.domain.model

sealed interface CloseDrawingResult {
    data object Success: CloseDrawingResult

    sealed interface Failure: CloseDrawingResult

    data object UnknownFailure: Failure
}