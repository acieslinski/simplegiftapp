package com.amc.acieslinski.simplegiftapp.drawingmanagement.data.repository.drawing

import com.amc.acieslinski.simplegiftapp.data.datasource.drawing.DrawingRemoteDataSource
import com.amc.acieslinski.simplegiftapp.data.datasource.token.PrivateTokenDataSource
import com.amc.acieslinski.simplegiftapp.drawingmanagement.domain.model.NewDrawing
import com.amc.acieslinski.simplegiftapp.drawingmanagement.domain.repositories.DrawingRepository
import com.amc.acieslinski.simplegiftapp.data.datasource.exception.withHandlingRepositoryExceptions
import com.amc.acieslinski.simplegiftapp.data.repository.Repository
import com.amc.acieslinski.simplegiftapp.drawingmanagement.data.repository.drawing.mapper.DrawingDataMapper
import com.amc.acieslinski.simplegiftapp.drawingmanagement.data.repository.drawing.mapper.SaveDrawingResultDataMapper
import com.amc.acieslinski.simplegiftapp.drawingmanagement.data.repository.drawing.mapper.SelectedDrawingResultMapper
import com.amc.acieslinski.simplegiftapp.drawingmanagement.domain.model.Drawing
import com.amc.acieslinski.simplegiftapp.drawingmanagement.domain.model.SaveDrawingResult
import com.amc.acieslinski.simplegiftapp.drawingmanagement.domain.model.SelectedDrawingResult
import com.amc.acieslinski.simplegiftapp.data.datasource.drawing.DrawingRemoteDataSource as SelectedDrawingDataSource

class DrawingRepositoryImpl(
    private val privateTokenDataSource: PrivateTokenDataSource,
    private val drawingRemoteDataSource: DrawingRemoteDataSource,
    private val selectedDrawingDataSource: SelectedDrawingDataSource,
    private val drawingDataMapper: DrawingDataMapper,
    private val saveDrawingResultDataMapper: SaveDrawingResultDataMapper,
    private val selectedDrawingResultMapper: SelectedDrawingResultMapper,
) : DrawingRepository, Repository() {

    override suspend fun createDrawing(newDrawing: NewDrawing): Result<Unit> =
        withHandlingRepositoryExceptions {
            val privateToken = privateTokenDataSource.getPrivateToken()
            drawingDataMapper.resolve(newDrawing, privateToken).run {
                drawingRemoteDataSource.saveDrawing(this)
            }
        }

    override suspend fun getSelectedDrawing(): SelectedDrawingResult {
        val privateToken = privateTokenDataSource.getPrivateToken()
        return drawingDataMapper.map(privateToken).run {
            selectedDrawingDataSource.getSelectedDrawing(this)
        }.let {
            selectedDrawingResultMapper.map(it)
        }
    }

    override suspend fun saveDrawing(drawing: Drawing): SaveDrawingResult {
        return tryCatching {
            val privateToken = privateTokenDataSource.getPrivateToken()
            drawingDataMapper.resolve(drawing, privateToken).run {
                drawingRemoteDataSource.saveDrawing(this)
            }
        }
            .let { saveDrawingResultDataMapper.map(it) }
    }
}