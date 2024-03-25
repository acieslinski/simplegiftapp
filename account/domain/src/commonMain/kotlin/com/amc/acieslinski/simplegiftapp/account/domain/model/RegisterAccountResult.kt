package com.amc.acieslinski.simplegiftapp.account.domain.model

sealed class RegisterAccountResult(
    val isSuccessful: Boolean = false
) {
    data object Success: RegisterAccountResult(isSuccessful = true)

    data object UnknownIssue: RegisterAccountResult()
}