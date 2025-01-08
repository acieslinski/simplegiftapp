package com.amc.acieslinski.simplegiftapp.drawingmanagement.data.repository.drawing.mapper

import com.amc.acieslinski.simplegiftapp.data.datasource.drawing.model.DrawingRemoteModel
import com.amc.acieslinski.simplegiftapp.drawingmanagement.domain.model.SelectedDrawingResult

class SelectedDrawingResultMapper(
    private val drawingDataMapper: DrawingDataMapper,
) {
    fun map(drawing: DrawingRemoteModel?): SelectedDrawingResult {
        val drawingId = drawing?.id
        return if (drawing != null && drawingId != null) {
            SelectedDrawingResult.Success(drawing = drawingDataMapper.resolve(drawingId, drawing))
        } else {
            SelectedDrawingResult.Empty
        }
    }
}