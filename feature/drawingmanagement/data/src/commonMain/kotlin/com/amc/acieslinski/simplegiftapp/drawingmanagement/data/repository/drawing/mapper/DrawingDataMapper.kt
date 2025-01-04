package com.amc.acieslinski.simplegiftapp.drawingmanagement.data.repository.drawing.mapper

import com.amc.acieslinski.simplegiftapp.data.datasource.drawing.model.DrawingRemoteModel
import com.amc.acieslinski.simplegiftapp.data.datasource.drawing.model.GetDrawingsRequestModel
import com.amc.acieslinski.simplegiftapp.drawingmanagement.domain.model.NewDrawing
import com.amc.acieslinski.simplegiftapp.drawingmanagement.data.datasource.drawing.model.CreateDrawingRequestModel
import com.amc.acieslinski.simplegiftapp.drawingmanagement.domain.model.Drawing
import com.amc.acieslinski.simplegiftapp.drawingmanagement.domain.model.SelectedDrawingResult
import kotlinx.datetime.Clock

class DrawingDataMapper {
    fun resolveData(newDrawing: NewDrawing, privateToken: String) = with(newDrawing) {
        CreateDrawingRequestModel(
            title = title,
            description = description,
            privateToken = privateToken,
        )
    }

    fun map(privateToken: String) = GetDrawingsRequestModel(
        privateToken = privateToken
    )

    fun mapToResult(drawing: DrawingRemoteModel?): SelectedDrawingResult = drawing?.let {
        SelectedDrawingResult.Success(drawing = map(it))
    } ?: SelectedDrawingResult.Empty

    private fun map(drawing: DrawingRemoteModel): Drawing = with(drawing) {
        Drawing(
            id = id,
            title = title,
            description = description,
            createdDate = createdDate ?: Clock.System.now(),
        )
    }
}