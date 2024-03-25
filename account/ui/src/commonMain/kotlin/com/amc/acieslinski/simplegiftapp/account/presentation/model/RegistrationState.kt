package com.amc.acieslinski.simplegiftapp.account.presentation.model

data class RegistrationState(
    val isDialogVisible: Boolean = false,
    val isRegistered: Boolean = false,
    val isLoading: Boolean = false,
    val error: RegistrationError? = null,
) {
    fun loading() = copy(isLoading = true, error = null, isRegistered = false)

    fun registered() = copy(isLoading = false, isRegistered = true, error = null)

    fun error(error: RegistrationError) =
        copy(isLoading = false, isRegistered = false, error = error)
}
