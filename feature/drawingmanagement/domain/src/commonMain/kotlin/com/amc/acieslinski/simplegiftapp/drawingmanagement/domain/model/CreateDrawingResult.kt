package com.amc.acieslinski.simplegiftapp.drawingmanagement.domain.model

sealed interface CreateDrawingResult {
    data object Success: CreateDrawingResult

    sealed interface Failure: CreateDrawingResult

    data object UnknownFailure: Failure
}