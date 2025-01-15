package com.amc.acieslinski.simplegiftapp.drawingmanagement.data.datasource.drawing

interface DrawingDrawRemoteDataSource {
    suspend fun drawParticipants(drawingId: String)
}