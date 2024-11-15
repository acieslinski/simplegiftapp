package com.amc.acieslinski.simplegiftapp.drawing.domain

import com.amc.acieslinski.simplegiftapp.drawing.domain.model.Drawing
import com.amc.acieslinski.simplegiftapp.drawing.domain.repositories.DrawingRepository

class CreateDrawingUseCase(private val drawingRepository: DrawingRepository) {
    operator fun invoke(drawing: Drawing) = drawingRepository.createDrawing(drawing)
}