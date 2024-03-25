package com.amc.acieslinski.simplegiftapp.android.ui.account.mapper

import com.amc.acieslinski.simplegiftapp.account.presentation.model.RegistrationDialogState
import com.amc.acieslinski.simplegiftapp.android.ui.account.model.RegistrationDialogUiModel

class RegistrationDialogStateMapper {
    fun mapToUi(dialogState: RegistrationDialogState): RegistrationDialogUiModel? {
        return when (dialogState) {
            is RegistrationDialogState.Error -> RegistrationDialogUiModel.UnknownFailure
            is RegistrationDialogState.Confirmation -> RegistrationDialogUiModel.Confirmation
            else -> null
        }
    }

    companion object {
        val INSTANCE = RegistrationDialogStateMapper()
    }
}