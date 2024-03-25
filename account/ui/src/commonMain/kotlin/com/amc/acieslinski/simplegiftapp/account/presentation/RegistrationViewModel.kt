package com.amc.acieslinski.simplegiftapp.account.presentation

import com.amc.acieslinski.simplegiftapp.BaseViewModel
import com.amc.acieslinski.simplegiftapp.account.domain.RegisterUseCase
import com.amc.acieslinski.simplegiftapp.account.presentation.model.RegistrationDialogState
import com.amc.acieslinski.simplegiftapp.account.presentation.model.RegistrationError
import com.amc.acieslinski.simplegiftapp.account.presentation.model.RegistrationState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update

class RegistrationViewModel(
    private val registerUseCase: RegisterUseCase
) : BaseViewModel() {
    private val _registrationState = MutableStateFlow(RegistrationState())
    val registrationState: StateFlow<RegistrationState> = _registrationState

    private val _registrationDialogStateState = MutableStateFlow<RegistrationDialogState>(
        RegistrationDialogState.Hidden)
    val registrationDialogState: StateFlow<RegistrationDialogState> = _registrationDialogStateState

    fun onRegisterAction(name: String, surname: String) {
        if (!_registrationState.value.isLoading) {
            _registrationState.update { it.loading() }
            registerUseCase(name, surname)
                .onEach { registerAccountResult ->
                    if (registerAccountResult.isSuccessful) {
                        _registrationState.update { it.registered() }
                        _registrationDialogStateState.emit(RegistrationDialogState.Confirmation)
                    } else {
                        with(RegistrationError.Unknown) {
                            _registrationState.update { it.error(this) }
                            _registrationDialogStateState.emit(RegistrationDialogState.Error(this))
                        }
                    }
                }
                .launchIn(scope)
        }
    }

    fun onNotificationAckAction() {
        _registrationDialogStateState.update { RegistrationDialogState.Hidden }
    }
}