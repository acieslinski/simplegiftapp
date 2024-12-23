package com.amc.acieslinski.simplegiftapp.dashboard.data.repository.drawing

import com.amc.acieslinski.simplegiftapp.dashboard.domain.model.Drawing
import com.amc.acieslinski.simplegiftapp.dashboard.domain.repositories.DrawingRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlin.time.Duration.Companion.seconds

class FakeDrawingRepositoryImpl : DrawingRepository {
    private var drawingId: String? = null

    override fun getDrawings(): Flow<List<Drawing>> = flow {
        emit(listOf(
            Drawing(
                id = "test-id",
                orderNumber = 1,
                title = "title",
                date = Instant.fromEpochMilliseconds(Clock.System.now().toEpochMilliseconds())
            )
        ))
    }

    override fun selectDrawing(drawingId: String) {
        this.drawingId = drawingId
    }
}