package com.amc.acieslinski.simplegiftapp.drawingmanagement.domain

import com.amc.acieslinski.simplegiftapp.drawingmanagement.domain.mapper.AddParticipantResultMapper
import com.amc.acieslinski.simplegiftapp.drawingmanagement.domain.model.AddParticipantResult
import com.amc.acieslinski.simplegiftapp.drawingmanagement.domain.model.SelectedDrawingResult
import com.amc.acieslinski.simplegiftapp.drawingmanagement.domain.model.User
import com.amc.acieslinski.simplegiftapp.drawingmanagement.domain.repositories.DrawingRepository

class AddParticipantToSelectedDrawingUseCase(
    private val drawingRepository: DrawingRepository,
    private val addParticipantResultMapper: AddParticipantResultMapper,
) {
    suspend operator fun invoke(user: User): AddParticipantResult {
        drawingRepository.getSelectedDrawing()
            .let { selectedDrawingResult ->
                if (selectedDrawingResult is SelectedDrawingResult.Success) {
                    selectedDrawingResult.drawing.participants.toMutableList().apply {
                        add(user)
                        selectedDrawingResult.drawing.copy(participants = this).run {
                            drawingRepository.saveDrawing(this).let {
                                return addParticipantResultMapper.map(it)
                            }
                        }
                    }
                } else {
                    return addParticipantResultMapper.map(selectedDrawingResult)
                }
            }
    }
}