package com.amc.acieslinski.simplegiftapp.drawingmanagement.domain

import com.amc.acieslinski.simplegiftapp.drawingmanagement.domain.model.CloseDrawingLobbyResult
import com.amc.acieslinski.simplegiftapp.drawingmanagement.domain.model.SelectedDrawingIdResult
import com.amc.acieslinski.simplegiftapp.drawingmanagement.domain.repositories.DrawingRepository

class CloseSelectedDrawingLobbyUseCase(
    private val drawingRepository: DrawingRepository
) {
    suspend operator fun invoke(): CloseDrawingLobbyResult {
        return when (val result = drawingRepository.getSelectedDrawingId()) {
            is SelectedDrawingIdResult.Success -> drawingRepository.closeLobby(result.id)
            else -> CloseDrawingLobbyResult.UnknownFailure
        }
    }
}