package com.amc.acieslinski.simplegiftapp.dashboard.domain.repositories

import com.amc.acieslinski.simplegiftapp.dashboard.domain.model.DrawingsResult

interface DrawingRepository {
    suspend fun getDrawings(): DrawingsResult

    suspend fun selectDrawingId(drawingId: String)
}