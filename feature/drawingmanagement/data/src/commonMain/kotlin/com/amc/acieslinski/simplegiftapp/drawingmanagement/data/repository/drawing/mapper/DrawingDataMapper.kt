package com.amc.acieslinski.simplegiftapp.drawingmanagement.data.repository.drawing.mapper

import com.amc.acieslinski.simplegiftapp.drawingmanagement.domain.model.Drawing
import com.amc.acieslinski.simplegiftapp.drawingmanagement.data.datasource.drawing.model.CreateDrawingRequestModel

class DrawingDataMapper {
    fun resolveData(drawing: Drawing, privateToken: String) = with(drawing) {
        CreateDrawingRequestModel(
            title = title,
            description = description,
            privateToken = privateToken,
        )
    }
}