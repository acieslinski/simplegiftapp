package com.amc.acieslinski.simplegiftapp.drawingmanagement.domain.model

sealed interface SaveDrawingResult {
    data object Success : SaveDrawingResult

    sealed interface Failure : SaveDrawingResult

    data object DrawingClosedFailure : Failure

    data object UnknownFailure : Failure
}