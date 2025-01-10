package com.amc.acieslinski.simplegiftapp.registration.presentation

import com.amc.acieslinski.simplegiftapp.presentation.BaseViewModel
import com.amc.acieslinski.simplegiftapp.registration.domain.IsUserRegisteredUseCase
import com.amc.acieslinski.simplegiftapp.registration.domain.RegisterUseCase
import com.amc.acieslinski.simplegiftapp.registration.domain.model.RegisterAccountResult
import com.amc.acieslinski.simplegiftapp.resources.Res
import com.amc.acieslinski.simplegiftapp.resources.account_register_confirmation_close
import com.amc.acieslinski.simplegiftapp.resources.account_register_failure
import com.amc.acieslinski.simplegiftapp.resources.account_register_success
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.StringResource

class RegistrationViewModel(
    private val registerUseCase: RegisterUseCase,
    private val isUserRegisteredUseCase: IsUserRegisteredUseCase,
) : BaseViewModel() {
    private val _registrationUiState = MutableStateFlow(RegistrationUiState())
    val registrationUiState: StateFlow<RegistrationUiState> = _registrationUiState

    private val _registrationDialogState = MutableStateFlow<RegistrationAlertState>(
        RegistrationAlertState.Hidden
    )
    val registrationDialogState: StateFlow<RegistrationAlertState> = _registrationDialogState

    init {
        scope.launch {
            if (isUserRegisteredUseCase()) {
                _registrationUiState.update { it.done() }
            }
        }
    }

    fun onRegisterAction(name: String, surname: String) {
        _registrationUiState.update { it.loading() }
        scope.launch {
            val registerResult = registerUseCase(name, surname)
            when (registerResult) {
                is RegisterAccountResult.Success -> {
                    _registrationUiState.update { it.registered() }
                    _registrationDialogState.update { RegistrationAlertState.Confirmation }
                }
                is RegisterAccountResult.UnknownFailure -> {
                    _registrationUiState.update { it.failed() }
                    _registrationDialogState.emit(
                        RegistrationAlertState.RegistrationUnknownFailure
                    )
                }
            }

        }
    }

    fun onNotificationAckAction() {
        _registrationDialogState.update { RegistrationAlertState.Hidden }
        _registrationUiState.update { it.done() }
    }
}

data class RegistrationUiState(
    val isRegistered: Boolean = false,
    val isLoading: Boolean = false,
    val isFailure: Boolean = false,
    val isRegistrationAck: Boolean = false
) {
    fun loading() = RegistrationUiState(
        isLoading = true,
        isFailure = false,
        isRegistered = false,
        isRegistrationAck = false
    )

    fun registered() = copy(isLoading = false, isRegistered = true, isFailure = false)

    fun failed() = RegistrationUiState(
        isLoading = false,
        isRegistered = false,
        isFailure = true,
        isRegistrationAck = false
    )

    fun done() = RegistrationUiState(
        isLoading = false,
        isRegistered = true,
        isFailure = false,
        isRegistrationAck = true
    )
}

sealed class RegistrationAlertState {
    val closeLabelRes: StringResource = Res.string.account_register_confirmation_close
    open val messageRes: StringResource = Res.string.account_register_success

    data object Confirmation : RegistrationAlertState()
    data object Hidden : RegistrationAlertState()

    sealed class Failure(override val messageRes: StringResource) : RegistrationAlertState()

    data object RegistrationUnknownFailure : Failure(Res.string.account_register_failure)
}
