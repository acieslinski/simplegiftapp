package com.amc.acieslinski.simplegiftapp.registration.domain.model

sealed class RegisterAccountResult(
    val isSuccessful: Boolean = false
) {
    data object Success: RegisterAccountResult(isSuccessful = true)

    data object UnknownFailure: RegisterAccountResult()
}