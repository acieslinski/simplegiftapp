package com.amc.acieslinski.simplegiftapp.account.presentation.model

sealed interface RegistrationDialogState {
    data object Confirmation : RegistrationDialogState

    data class Error(val error: RegistrationError) : RegistrationDialogState

    data object Hidden : RegistrationDialogState
}