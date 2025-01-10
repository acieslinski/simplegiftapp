package com.amc.acieslinski.simplegiftapp.drawingmanagement.domain

import com.amc.acieslinski.simplegiftapp.drawingmanagement.domain.model.SelectedDrawingUpdate
import com.amc.acieslinski.simplegiftapp.drawingmanagement.domain.repositories.DrawingRepository
import kotlinx.coroutines.flow.Flow

class ObserveSelectedDrawingUseCase(
    private val drawingRepository: DrawingRepository
) {
    operator fun invoke(): Flow<SelectedDrawingUpdate> =
        drawingRepository.observeSelectedDrawing()
}