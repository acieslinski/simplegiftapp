package com.amc.acieslinski.simplegiftapp.data.repository.drawing

import com.amc.acieslinski.simplegiftapp.data.datasource.drawing.DrawingRemoteDataSource
import com.amc.acieslinski.simplegiftapp.data.datasource.token.PrivateTokenDataSource
import com.amc.acieslinski.simplegiftapp.drawingmanagement.domain.model.Drawing
import com.amc.acieslinski.simplegiftapp.drawingmanagement.domain.repositories.DrawingRepository
import com.amc.acieslinski.simplegiftapp.data.datasource.exception.withHandlingRepositoryExceptions
import com.amc.acieslinski.simplegiftapp.data.repository.drawing.mapper.DrawingDataMapper

class DrawingRepositoryImpl(
    private val privateTokenDataSource: PrivateTokenDataSource,
    private val drawingRemoteDataSource: DrawingRemoteDataSource,
    private val drawingDataMapper: DrawingDataMapper,
) : DrawingRepository {

    override suspend fun createDrawing(drawing: Drawing): Result<Unit> =
        withHandlingRepositoryExceptions {
            val privateToken = privateTokenDataSource.getPrivateToken()
            drawingDataMapper.resolveData(drawing, privateToken).run {
                drawingRemoteDataSource.createDrawing(this)
            }
        }
}