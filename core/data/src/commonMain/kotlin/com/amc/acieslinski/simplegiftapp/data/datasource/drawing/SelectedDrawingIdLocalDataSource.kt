package com.amc.acieslinski.simplegiftapp.data.datasource.drawing

import kotlinx.coroutines.flow.Flow

interface SelectedDrawingIdLocalDataSource {
    suspend fun selectDrawingId(drawingId: String)

    suspend fun getSelectedDrawingId(): String?

    fun observeSelectedDrawingId(): Flow<String>
}