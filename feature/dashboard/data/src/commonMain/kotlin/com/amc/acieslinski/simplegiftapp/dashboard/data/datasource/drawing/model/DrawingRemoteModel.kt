package com.amc.acieslinski.simplegiftapp.dashboard.data.datasource.drawing.model

import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable

@Serializable
data class DrawingRemoteModel(
    val id: String,
    val title: String,
    val createdDate: Instant? = null,
)