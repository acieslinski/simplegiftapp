package com.amc.acieslinski.simplegiftapp.data.datasource.drawing

import com.amc.acieslinski.simplegiftapp.data.datasource.drawing.model.CreateDrawingRequestModel
import com.amc.acieslinski.simplegiftapp.data.datasource.exception.RequestException
import kotlin.coroutines.cancellation.CancellationException

interface DrawingRemoteDataSource {
    @Throws(RequestException::class, CancellationException::class)
    suspend fun createDrawing(drawing: CreateDrawingRequestModel)
}