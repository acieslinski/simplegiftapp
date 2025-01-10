package com.amc.acieslinski.simplegiftapp.dashboard.data.repository.drawing

import com.amc.acieslinski.simplegiftapp.data.datasource.drawing.DrawingRemoteDataSource
import com.amc.acieslinski.simplegiftapp.dashboard.domain.model.Drawing
import com.amc.acieslinski.simplegiftapp.dashboard.domain.model.DrawingsResult
import com.amc.acieslinski.simplegiftapp.dashboard.domain.repositories.DrawingRepository
import com.amc.acieslinski.simplegiftapp.data.datasource.drawing.SelectedDrawingIdLocalDataSource
import com.amc.acieslinski.simplegiftapp.data.datasource.drawing.model.DrawingResponseModel
import com.amc.acieslinski.simplegiftapp.data.repository.Repository

class DrawingLiveRepository(
    private val drawingRemoteDataSource: DrawingRemoteDataSource,
    private val selectedDrawingIdLocalDataSource: SelectedDrawingIdLocalDataSource,
) : DrawingRepository, Repository() {

    override suspend fun getDrawings(): DrawingsResult {
        return tryCatching(
            action = {
                val drawings = drawingRemoteDataSource.getDrawings()
                    .sortedByDescending { it.createdDate }
                    .mapIndexed { index, drawingRemoteModel ->
                        drawingRemoteModel.toDomain(index)
                    }
                DrawingsResult.Success(drawings)
            },
            error = { DrawingsResult.UnknownFailure(it) }
        )
    }

    override suspend fun selectDrawingId(drawingId: String) =
        selectedDrawingIdLocalDataSource.selectDrawingId(drawingId)
}

fun DrawingResponseModel.toDomain(indexNumber: Int) = Drawing(
    id = id,
    orderNumber = indexNumber + 1,
    title = title,
    createdDate = createdDate
)