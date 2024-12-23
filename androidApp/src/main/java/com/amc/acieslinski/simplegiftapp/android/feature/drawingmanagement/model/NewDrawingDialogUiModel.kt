package com.amc.acieslinski.simplegiftapp.android.feature.drawingmanagement.model

import com.amc.acieslinski.simplegiftapp.resources.Res
import com.amc.acieslinski.simplegiftapp.resources.drawing_created_failure
import com.amc.acieslinski.simplegiftapp.resources.drawing_created_success
import com.amc.acieslinski.simplegiftapp.resources.drawing_creation_alert_close
import org.jetbrains.compose.resources.StringResource


// TODO move the code to the presentation
sealed class NewDrawingDialogUiModel(
    val messageStringId: StringResource,
    val closeStringId: StringResource = Res.string.drawing_creation_alert_close,
) {
    data object Confirmation :
        NewDrawingDialogUiModel(
            messageStringId = Res.string.drawing_created_success,
        )

    data object UnknownFailure :
        NewDrawingDialogUiModel(
            messageStringId = Res.string.drawing_created_failure,
        )
}