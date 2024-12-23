package com.amc.acieslinski.simplegiftapp.drawingmanagement.data.datasource.drawing.mapper

import com.amc.acieslinski.simplegiftapp.drawingmanagement.data.datasource.drawing.model.DrawingRemoteModel
import com.amc.acieslinski.simplegiftapp.drawingmanagement.data.datasource.drawing.model.CreateDrawingRequestModel

class DrawingRemoteMapper {
    fun toRemote(model: CreateDrawingRequestModel) = with(model) {
        DrawingRemoteModel(
            title = title,
            description = description,
            participantsPublicToken = emptyList(),
            drawnParticipantPublicToken = null,
            isDrawingClosed = false,
        )
    }
}