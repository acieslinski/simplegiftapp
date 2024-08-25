package com.amc.acieslinski.simplegiftapp.android.ui.account.model

import com.amc.acieslinski.simplegiftapp.resources.Res
import com.amc.acieslinski.simplegiftapp.resources.account_register_failure
import com.amc.acieslinski.simplegiftapp.resources.account_register_success
import org.jetbrains.compose.resources.StringResource

sealed class RegistrationDialogUiModel(
    val messageId: StringResource
) {
    data object Confirmation :
        RegistrationDialogUiModel(Res.string.account_register_success)

    data object UnknownFailure :
        RegistrationDialogUiModel(Res.string.account_register_failure)
}