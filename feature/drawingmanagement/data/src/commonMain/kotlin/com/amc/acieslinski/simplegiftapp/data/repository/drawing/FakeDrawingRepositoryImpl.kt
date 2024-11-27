package com.amc.acieslinski.simplegiftapp.data.repository.drawing

import com.amc.acieslinski.simplegiftapp.drawingmanagement.domain.model.Drawing
import com.amc.acieslinski.simplegiftapp.drawingmanagement.domain.repositories.DrawingRepository
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.seconds

class FakeDrawingRepositoryImpl : DrawingRepository {
    override suspend fun createDrawing(drawing: Drawing): Result<Unit> {
        delay(2.seconds)
        return Result.success(Unit)
    }
}