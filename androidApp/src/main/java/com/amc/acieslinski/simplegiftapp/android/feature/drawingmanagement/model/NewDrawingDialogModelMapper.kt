package com.amc.acieslinski.simplegiftapp.android.feature.drawingmanagement.model

import com.amc.acieslinski.simplegiftapp.drawingmanagement.presentation.model.NewDrawingAlertState

object NewDrawingDialogModelMapper {
    fun mapToUiModel(dialogState: NewDrawingAlertState): NewDrawingDialogUiModel? {
        return when (dialogState) {
            is NewDrawingAlertState.SaveError -> NewDrawingDialogUiModel.UnknownFailure
            is NewDrawingAlertState.Confirmation -> NewDrawingDialogUiModel.Confirmation
            else -> null
        }
    }
}