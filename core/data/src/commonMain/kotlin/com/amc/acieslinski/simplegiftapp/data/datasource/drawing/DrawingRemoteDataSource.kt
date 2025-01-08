package com.amc.acieslinski.simplegiftapp.data.datasource.drawing

import com.amc.acieslinski.simplegiftapp.data.datasource.drawing.model.DrawingRemoteModel
import com.amc.acieslinski.simplegiftapp.data.datasource.drawing.model.GetDrawingsRequestModel
import com.amc.acieslinski.simplegiftapp.data.datasource.drawing.model.SaveDrawingRequestModel
import com.amc.acieslinski.simplegiftapp.data.datasource.exception.RequestException
import kotlin.coroutines.cancellation.CancellationException

interface DrawingRemoteDataSource {
    @Throws(RequestException::class, CancellationException::class)
    suspend fun getDrawings(request: GetDrawingsRequestModel): List<DrawingRemoteModel>

    suspend fun selectDrawing(drawingId: String)

    @Throws(RequestException::class, CancellationException::class)
    suspend fun getSelectedDrawing(request: GetDrawingsRequestModel): DrawingRemoteModel?

    @Throws(RequestException::class, CancellationException::class)
    suspend fun saveDrawing(request: SaveDrawingRequestModel)
}