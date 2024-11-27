package com.amc.acieslinski.simplegiftapp.drawingmanagement.domain.repositories

import com.amc.acieslinski.simplegiftapp.drawingmanagement.domain.model.Drawing

interface DrawingRepository {
    suspend fun createDrawing(drawing: Drawing): Result<Unit>
}