package com.amc.acieslinski.simplegiftapp.drawingmanagement.presentation.model

import com.amc.acieslinski.simplegiftapp.drawingmanagement.domain.model.Drawing

data class NewDrawingUiState (
    val title: String = "",
    val description: String = "",
    val isSaved: Boolean = false,
    val isError: Boolean = false,
    val isSaveAck: Boolean = false,
    val isCancelled: Boolean = false,
) {
    fun toDomain() = Drawing(
        title = title,
        description = description,
        participants = emptyList(),
        drawnParticipant = null,
        isDrawingClosed = false
    )
}