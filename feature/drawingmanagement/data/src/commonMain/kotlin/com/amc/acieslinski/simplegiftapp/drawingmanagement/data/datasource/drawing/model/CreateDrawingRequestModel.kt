package com.amc.acieslinski.simplegiftapp.drawingmanagement.data.datasource.drawing.model

data class CreateDrawingRequestModel(
    val title: String,
    val description: String,
    val privateToken: String,
)