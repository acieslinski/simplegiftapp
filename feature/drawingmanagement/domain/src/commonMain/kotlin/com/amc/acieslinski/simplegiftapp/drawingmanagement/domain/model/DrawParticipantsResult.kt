package com.amc.acieslinski.simplegiftapp.drawingmanagement.domain.model

sealed interface DrawParticipantsResult {
    data object Success: DrawParticipantsResult

    sealed interface Failure: DrawParticipantsResult

    data object UnknownFailure: Failure
}