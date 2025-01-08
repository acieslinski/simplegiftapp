package com.amc.acieslinski.simplegiftapp.drawingmanagement.data.repository.drawing.mapper

import com.amc.acieslinski.simplegiftapp.data.datasource.drawing.model.DrawingRemoteModel
import com.amc.acieslinski.simplegiftapp.data.datasource.drawing.model.GetDrawingsRequestModel
import com.amc.acieslinski.simplegiftapp.drawingmanagement.domain.model.NewDrawing
import com.amc.acieslinski.simplegiftapp.data.datasource.drawing.model.SaveDrawingRequestModel
import com.amc.acieslinski.simplegiftapp.drawingmanagement.domain.model.Drawing
import kotlinx.datetime.Clock

class DrawingDataMapper {
    fun resolve(newDrawing: NewDrawing, privateToken: String) = with(newDrawing) {
        SaveDrawingRequestModel(
            drawing = DrawingRemoteModel(
                title = title,
                description = description,
                createdDate = Clock.System.now(),
                participantsPublicToken = emptyList(),
            ),
            privateToken = privateToken,
        )
    }

    fun resolve(drawing: Drawing, privateToken: String) = SaveDrawingRequestModel(
        drawing = map(drawing),
        privateToken = privateToken,
    )

    fun map(privateToken: String) = GetDrawingsRequestModel(privateToken = privateToken)

    fun resolve(drawingId: String, drawing: DrawingRemoteModel): Drawing = with(drawing) {
        Drawing(
            id = drawingId,
            title = title,
            description = description,
            createdDate = createdDate,
            participants = emptyList(),
        )
    }

    private fun map(drawing: Drawing): DrawingRemoteModel = with(drawing) {
        DrawingRemoteModel(
            id = id,
            title = title,
            description = description,
            createdDate = createdDate,
            participantsPublicToken = drawing.participants.map { it.idToken },
        )
    }
}