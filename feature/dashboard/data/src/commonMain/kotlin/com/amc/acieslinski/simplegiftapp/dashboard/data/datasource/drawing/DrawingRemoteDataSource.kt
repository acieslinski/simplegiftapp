package com.amc.acieslinski.simplegiftapp.dashboard.data.datasource.drawing

import com.amc.acieslinski.simplegiftapp.dashboard.data.datasource.drawing.model.DrawingRemoteModel
import com.amc.acieslinski.simplegiftapp.dashboard.data.datasource.drawing.model.GetDrawingsRequestModel
import com.amc.acieslinski.simplegiftapp.data.datasource.exception.RequestException
import kotlin.coroutines.cancellation.CancellationException

interface DrawingRemoteDataSource {
    @Throws(RequestException::class, CancellationException::class)
    suspend fun getDrawings(request: GetDrawingsRequestModel): List<DrawingRemoteModel>
}