package com.amc.acieslinski.simplegiftapp.drawingmanagement.domain

import com.amc.acieslinski.simplegiftapp.drawingmanagement.domain.model.SelectedDrawingResult
import com.amc.acieslinski.simplegiftapp.drawingmanagement.domain.repositories.DrawingRepository

class GetSelectedDrawingUseCase(
    private val drawingRepository: DrawingRepository
) {
    suspend operator fun invoke(): SelectedDrawingResult = drawingRepository.getSelectedDrawing()
}