package com.amc.acieslinski.simplegiftapp.data.datasource.drawing.model

data class SaveDrawingRequestModel(
    val drawing: DrawingRemoteModel,
    val privateToken: String,
)