package com.amc.acieslinski.simplegiftapp.dashboard.domain

import com.amc.acieslinski.simplegiftapp.dashboard.domain.repositories.DrawingRepository

class SelectDrawingUseCase(
    private val drawingRepository: DrawingRepository
) {
    suspend operator fun invoke(drawingId: String) = drawingRepository.selectDrawingId(drawingId)
}