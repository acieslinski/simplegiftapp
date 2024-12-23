package com.amc.acieslinski.simplegiftapp.dashboard.domain.repositories

import com.amc.acieslinski.simplegiftapp.dashboard.domain.model.Drawing
import kotlinx.coroutines.flow.Flow

interface DrawingRepository {
    fun getDrawings(): Flow<List<Drawing>>

    fun selectDrawing(drawingId: String)
}