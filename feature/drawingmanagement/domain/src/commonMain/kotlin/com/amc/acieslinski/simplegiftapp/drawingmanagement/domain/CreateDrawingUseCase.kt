package com.amc.acieslinski.simplegiftapp.drawingmanagement.domain

import com.amc.acieslinski.simplegiftapp.drawingmanagement.domain.model.Drawing
import com.amc.acieslinski.simplegiftapp.drawingmanagement.domain.repositories.DrawingRepository

class CreateDrawingUseCase(private val drawingRepository: DrawingRepository) {
    suspend operator fun invoke(drawing: Drawing): Result<Unit> =
        drawingRepository.createDrawing(drawing)
}