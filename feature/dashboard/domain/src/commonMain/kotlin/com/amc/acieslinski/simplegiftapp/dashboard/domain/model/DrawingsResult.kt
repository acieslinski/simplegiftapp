package com.amc.acieslinski.simplegiftapp.dashboard.domain.model

sealed interface DrawingsResult {
    data class Success(
        val drawings: List<Drawing>
    ): DrawingsResult

    sealed interface Failure: DrawingsResult

    data class UnknownFailure(val e: Throwable): Failure
}