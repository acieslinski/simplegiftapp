package com.amc.acieslinski.simplegiftapp.drawingmanagement.domain

import com.amc.acieslinski.simplegiftapp.drawingmanagement.domain.model.CloseDrawingLobbyResult
import com.amc.acieslinski.simplegiftapp.drawingmanagement.domain.model.CloseDrawingResult
import com.amc.acieslinski.simplegiftapp.drawingmanagement.domain.model.DrawParticipantsResult

class CloseSelectedDrawingUseCase(
    private val closeSelectedDrawingLobbyUseCase: CloseSelectedDrawingLobbyUseCase,
    private val drawSelectedDrawingParticipantsUseCase: DrawSelectedDrawingParticipantsUseCase,
) {
    suspend operator fun invoke(): CloseDrawingResult {
        return when (closeSelectedDrawingLobbyUseCase()) {
            is CloseDrawingLobbyResult.Success -> {
                when (drawSelectedDrawingParticipantsUseCase()) {
                    is DrawParticipantsResult.Success -> CloseDrawingResult.Success
                    is DrawParticipantsResult.UnknownFailure -> CloseDrawingResult.UnknownFailure
                }
            }
            is CloseDrawingLobbyResult.UnknownFailure -> CloseDrawingResult.UnknownFailure
        }
    }
}