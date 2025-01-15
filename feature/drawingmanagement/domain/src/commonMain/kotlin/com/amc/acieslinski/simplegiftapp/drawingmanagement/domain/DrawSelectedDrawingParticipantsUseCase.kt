package com.amc.acieslinski.simplegiftapp.drawingmanagement.domain

import com.amc.acieslinski.simplegiftapp.drawingmanagement.domain.model.DrawParticipantsResult
import com.amc.acieslinski.simplegiftapp.drawingmanagement.domain.model.SelectedDrawingIdResult
import com.amc.acieslinski.simplegiftapp.drawingmanagement.domain.repositories.DrawingRepository

class DrawSelectedDrawingParticipantsUseCase(
    private val repository: DrawingRepository
) {
    suspend operator fun invoke(): DrawParticipantsResult {
        return when (val result = repository.getSelectedDrawingId()) {
            is SelectedDrawingIdResult.Success -> repository.drawParticipants(result.id)
            is SelectedDrawingIdResult.Empty -> { DrawParticipantsResult.UnknownFailure }
        }
    }
}