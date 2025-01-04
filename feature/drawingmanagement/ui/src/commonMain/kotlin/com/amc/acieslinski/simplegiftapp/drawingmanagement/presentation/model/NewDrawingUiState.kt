package com.amc.acieslinski.simplegiftapp.drawingmanagement.presentation.model

import com.amc.acieslinski.simplegiftapp.drawingmanagement.domain.model.NewDrawing

data class NewDrawingUiState (
    val title: String = "",
    val description: String = "",
    val isSaved: Boolean = false,
    val isError: Boolean = false,
    val isSaveAck: Boolean = false,
    val isCancelled: Boolean = false,
) {
    fun toDomain() = NewDrawing(
        title = title,
        description = description,
    )
}