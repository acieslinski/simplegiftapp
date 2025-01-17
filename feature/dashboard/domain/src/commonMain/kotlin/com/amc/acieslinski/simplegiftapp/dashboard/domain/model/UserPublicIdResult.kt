package com.amc.acieslinski.simplegiftapp.dashboard.domain.model

sealed interface UserPublicIdResult {
    data class Success(val userId: String): UserPublicIdResult

    sealed interface Failure: UserPublicIdResult

    data object UnknownFailure: Failure
}