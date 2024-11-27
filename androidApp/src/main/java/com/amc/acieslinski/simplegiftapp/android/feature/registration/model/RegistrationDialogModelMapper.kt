package com.amc.acieslinski.simplegiftapp.android.feature.registration.model

import com.amc.acieslinski.simplegiftapp.registration.presentation.model.RegistrationDialogState

object RegistrationDialogModelMapper {
    fun mapToUiModel(dialogState: RegistrationDialogState): RegistrationDialogUiModel? {
        return when (dialogState) {
            is RegistrationDialogState.Error -> RegistrationDialogUiModel.UnknownFailure
            is RegistrationDialogState.Confirmation -> RegistrationDialogUiModel.Confirmation
            else -> null
        }
    }
}