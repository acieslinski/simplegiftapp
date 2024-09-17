package com.amc.acieslinski.simplegiftapp.android.ui.account.model

import com.amc.acieslinski.simplegiftapp.account.presentation.model.RegistrationDialogState
import com.amc.acieslinski.simplegiftapp.android.ui.account.model.RegistrationDialogUiModel

object RegistrationDialogModelMapper {
    fun mapToUiModel(dialogState: RegistrationDialogState): RegistrationDialogUiModel? {
        return when (dialogState) {
            is RegistrationDialogState.Error -> RegistrationDialogUiModel.UnknownFailure
            is RegistrationDialogState.Confirmation -> RegistrationDialogUiModel.Confirmation
            else -> null
        }
    }
}