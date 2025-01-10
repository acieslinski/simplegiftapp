package com.amc.acieslinski.simplegiftapp.dashboard.domain

import com.amc.acieslinski.simplegiftapp.dashboard.domain.model.DrawingsResult
import com.amc.acieslinski.simplegiftapp.dashboard.domain.repositories.DrawingRepository

class GetDrawingsUseCase(
    private val repository: DrawingRepository
) {
    suspend operator fun invoke(): DrawingsResult = repository.getDrawings()
}