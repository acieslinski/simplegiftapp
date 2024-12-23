package com.amc.acieslinski.simplegiftapp.dashboard.domain

import com.amc.acieslinski.simplegiftapp.dashboard.domain.model.Drawing
import com.amc.acieslinski.simplegiftapp.dashboard.domain.repositories.DrawingRepository
import kotlinx.coroutines.flow.Flow

class GetDrawingsUseCase(
    private val repository: DrawingRepository
) {
    /**
     * Fetches list of drawings. In case of any error the list will be empty.
     */
    operator fun invoke(): Flow<List<Drawing>> = repository.getDrawings()
}