package com.amc.acieslinski.simplegiftapp.dashboard.data.repository.drawing

import com.amc.acieslinski.simplegiftapp.dashboard.domain.model.Drawing
import com.amc.acieslinski.simplegiftapp.dashboard.domain.model.DrawingsResult
import com.amc.acieslinski.simplegiftapp.dashboard.domain.repositories.DrawingRepository
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant

class DrawingFakeRepository : DrawingRepository {
    private var drawingId: String? = null

    override suspend fun getDrawings(): DrawingsResult =
            DrawingsResult.Success(
                listOf(
                    Drawing(
                        id = "test-id",
                        orderNumber = 1,
                        title = "title",
                        createdDate = Instant.fromEpochMilliseconds(
                            Clock.System.now().toEpochMilliseconds()
                        )
                    )
                )
            )

    override suspend fun selectDrawingId(drawingId: String) {
        this.drawingId = drawingId
    }
}