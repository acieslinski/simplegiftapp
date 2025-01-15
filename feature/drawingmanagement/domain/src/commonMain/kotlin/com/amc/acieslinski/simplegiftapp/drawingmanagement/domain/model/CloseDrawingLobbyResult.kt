package com.amc.acieslinski.simplegiftapp.drawingmanagement.domain.model

sealed interface CloseDrawingLobbyResult {
    open class Success: CloseDrawingLobbyResult

    data object AlreadyClosed: Success()

    sealed interface Failure: CloseDrawingLobbyResult

    data object UnknownFailure: Failure
}