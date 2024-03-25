package com.amc.acieslinski.simplegiftapp.android.ui.account.model

import androidx.annotation.StringRes
import com.amc.acieslinski.simplegiftapp.android.R

sealed class RegistrationDialogUiModel(
    @StringRes val messageId: Int
) {
    data object Confirmation :
        RegistrationDialogUiModel(R.string.registration_success)

    data object UnknownFailure :
        RegistrationDialogUiModel(R.string.registration_failure)
}