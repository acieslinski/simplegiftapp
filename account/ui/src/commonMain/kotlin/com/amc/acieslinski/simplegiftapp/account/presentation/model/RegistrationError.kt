package com.amc.acieslinski.simplegiftapp.account.presentation.model

sealed interface RegistrationError {
    data object Unknown: RegistrationError
}