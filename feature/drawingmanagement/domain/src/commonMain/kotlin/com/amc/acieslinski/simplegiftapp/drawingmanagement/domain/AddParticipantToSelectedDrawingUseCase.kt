package com.amc.acieslinski.simplegiftapp.drawingmanagement.domain

import com.amc.acieslinski.simplegiftapp.drawingmanagement.domain.model.AddParticipantResult
import com.amc.acieslinski.simplegiftapp.drawingmanagement.domain.model.SelectedDrawingIdResult
import com.amc.acieslinski.simplegiftapp.drawingmanagement.domain.repositories.DrawingRepository

class AddParticipantToSelectedDrawingUseCase(
    private val drawingRepository: DrawingRepository,
) {
    suspend operator fun invoke(userId: String): AddParticipantResult {
        return when (val result = drawingRepository.getSelectedDrawingId()) {
            is SelectedDrawingIdResult.Success -> {
                drawingRepository.addParticipant(result.id, userId)
            }
            else -> AddParticipantResult.UnknownFailure
        }
    }
}