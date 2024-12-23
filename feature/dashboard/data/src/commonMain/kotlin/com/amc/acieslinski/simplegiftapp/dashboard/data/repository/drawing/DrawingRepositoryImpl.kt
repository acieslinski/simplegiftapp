package com.amc.acieslinski.simplegiftapp.dashboard.data.repository.drawing

import com.amc.acieslinski.simplegiftapp.dashboard.data.datasource.drawing.DrawingRemoteDataSource
import com.amc.acieslinski.simplegiftapp.data.datasource.token.PrivateTokenDataSource
import com.amc.acieslinski.simplegiftapp.dashboard.domain.model.Drawing
import com.amc.acieslinski.simplegiftapp.dashboard.domain.repositories.DrawingRepository
import com.amc.acieslinski.simplegiftapp.dashboard.data.repository.drawing.mapper.DrawingDataMapper
import com.amc.acieslinski.simplegiftapp.data.datasource.exception.withHandlingRepositoryExceptions
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class DrawingRepositoryImpl(
    private val privateTokenDataSource: PrivateTokenDataSource,
    private val drawingRemoteDataSource: DrawingRemoteDataSource,
    private val drawingDataMapper: DrawingDataMapper,
) : DrawingRepository {
    private var drawingId: String? = null

    override fun getDrawings(): Flow<List<Drawing>> = flow {
        val privateToken = privateTokenDataSource.getPrivateToken()
        val request = drawingDataMapper.map(privateToken)
        val result = withHandlingRepositoryExceptions {
            drawingRemoteDataSource.getDrawings(request)
                .sortedByDescending { it.createdDate }
                .mapIndexed { index, drawingRemoteModel ->
                    drawingDataMapper.resolve(index, drawingRemoteModel)
                }
        }
        emit(result.getOrElse { emptyList() })
    }

    override fun selectDrawing(drawingId: String) {
        this.drawingId = drawingId
    }
}