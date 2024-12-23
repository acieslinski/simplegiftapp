package com.amc.acieslinski.simplegiftapp.dashboard.presentation.mapper

import com.amc.acieslinski.simplegiftapp.dashboard.domain.model.Drawing
import com.amc.acieslinski.simplegiftapp.dashboard.presentation.model.DrawingUiState

object DrawingMapper {
    fun mapToUiState(drawing: Drawing) = with(drawing) {
        DrawingUiState(
            id = id,
            orderNumber = orderNumber,
            title = title,
            date = date
        )
    }
}