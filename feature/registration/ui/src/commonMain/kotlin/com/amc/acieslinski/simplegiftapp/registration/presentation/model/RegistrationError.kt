package com.amc.acieslinski.simplegiftapp.registration.presentation.model

sealed interface RegistrationError {
    data object Unknown: RegistrationError
}