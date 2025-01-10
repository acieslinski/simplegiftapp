package com.amc.acieslinski.simplegiftapp.data.datasource.drawing

import com.amc.acieslinski.simplegiftapp.data.datasource.drawing.model.DrawingRequestModel
import com.amc.acieslinski.simplegiftapp.data.datasource.drawing.model.DrawingResponseModel
import com.amc.acieslinski.simplegiftapp.data.datasource.exception.RequestException
import kotlin.coroutines.cancellation.CancellationException

interface DrawingRemoteDataSource {
    @Throws(RequestException::class, CancellationException::class)
    suspend fun getDrawings(): List<DrawingResponseModel>

    @Throws(RequestException::class, CancellationException::class)
    suspend fun saveDrawing(drawing: DrawingRequestModel)
}