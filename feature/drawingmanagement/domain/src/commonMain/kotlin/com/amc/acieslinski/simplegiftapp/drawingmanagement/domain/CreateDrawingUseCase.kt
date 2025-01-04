package com.amc.acieslinski.simplegiftapp.drawingmanagement.domain

import com.amc.acieslinski.simplegiftapp.drawingmanagement.domain.model.NewDrawing
import com.amc.acieslinski.simplegiftapp.drawingmanagement.domain.repositories.DrawingRepository

class CreateDrawingUseCase(private val drawingRepository: DrawingRepository) {
    suspend operator fun invoke(newDrawing: NewDrawing): Result<Unit> =
        drawingRepository.createDrawing(newDrawing)
}