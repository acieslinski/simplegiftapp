package com.amc.acieslinski.simplegiftapp.drawingmanagement.domain.repositories

import com.amc.acieslinski.simplegiftapp.drawingmanagement.domain.model.Drawing
import com.amc.acieslinski.simplegiftapp.drawingmanagement.domain.model.NewDrawing
import com.amc.acieslinski.simplegiftapp.drawingmanagement.domain.model.SaveDrawingResult
import com.amc.acieslinski.simplegiftapp.drawingmanagement.domain.model.SelectedDrawingResult

interface DrawingRepository {
    // TODO use result sealed class, make it free of exceptions
    suspend fun createDrawing(newDrawing: NewDrawing): Result<Unit>

    suspend fun getSelectedDrawing(): SelectedDrawingResult

    suspend fun saveDrawing(drawing: Drawing): SaveDrawingResult
}