package com.amc.acieslinski.simplegiftapp.drawingmanagement.domain.model

sealed interface AddParticipantResult {
    data object Success : AddParticipantResult

    sealed interface Failure : AddParticipantResult

    data object UnknownFailure : Failure
}