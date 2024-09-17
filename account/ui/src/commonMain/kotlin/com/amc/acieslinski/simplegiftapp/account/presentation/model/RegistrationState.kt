package com.amc.acieslinski.simplegiftapp.account.presentation.model

data class RegistrationState(
    val isRegistered: Boolean = false,
    val isLoading: Boolean = false,
    val error: RegistrationError? = null,
    val isRegistrationAck: Boolean = false
) {
    fun loading() = RegistrationState(
        isLoading = true,
        error = null,
        isRegistered = false,
        isRegistrationAck = false
    )

    fun registered() = copy(isLoading = false, isRegistered = true, error = null)

    fun error(error: RegistrationError) = RegistrationState(
        isLoading = false,
        isRegistered = false,
        error = error,
        isRegistrationAck = false
    )

    fun done() = RegistrationState(
        isLoading = false,
        isRegistered = true,
        error = null,
        isRegistrationAck = true
    )
}
