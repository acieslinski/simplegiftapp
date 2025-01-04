package com.amc.acieslinski.simplegiftapp.drawingmanagement.data.repository.drawing

import com.amc.acieslinski.simplegiftapp.drawingmanagement.domain.model.Drawing
import com.amc.acieslinski.simplegiftapp.drawingmanagement.domain.model.NewDrawing
import com.amc.acieslinski.simplegiftapp.drawingmanagement.domain.model.SelectedDrawingResult
import com.amc.acieslinski.simplegiftapp.drawingmanagement.domain.repositories.DrawingRepository
import kotlinx.coroutines.delay
import kotlinx.datetime.Clock
import kotlin.time.Duration.Companion.seconds

class FakeDrawingRepositoryImpl : DrawingRepository {
    override suspend fun createDrawing(newDrawing: NewDrawing): Result<Unit> {
        delay(2.seconds)
        return Result.success(Unit)
    }

    override suspend fun getSelectedDrawing(): SelectedDrawingResult {
        return SelectedDrawingResult.Success(
            drawing = Drawing(
                id = "test-id",
                title = "Test drawing",
                description = "Test drawing of participants pairs",
                createdDate = Clock.System.now(),
            )
        )
    }
}