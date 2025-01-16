package com.amc.acieslinski.simplegiftapp.drawingmanagement.domain

import com.amc.acieslinski.simplegiftapp.drawingmanagement.domain.model.SelectedDrawingUpdate
import com.amc.acieslinski.simplegiftapp.drawingmanagement.domain.repositories.DrawingRepository
import kotlinx.coroutines.flow.first

class IsParticipantAddedToSelectedDrawingUseCase(
    private val drawingRepository: DrawingRepository,
) {
    suspend operator fun invoke(userId: String): Boolean {
        return when (val result = drawingRepository.observeSelectedDrawing().first()) {
            is SelectedDrawingUpdate.Success -> {
                result.drawing.participants.firstOrNull { it.id == userId} != null
            }
            else -> false
        }
    }
}