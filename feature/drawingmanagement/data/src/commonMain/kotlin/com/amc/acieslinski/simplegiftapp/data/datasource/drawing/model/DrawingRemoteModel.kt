package com.amc.acieslinski.simplegiftapp.data.datasource.drawing.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DrawingRemoteModel(
    val id: String? = null,
    val title: String,
    val description: String,
    @SerialName("participants")
    val participantsPublicToken: List<String>,
    @SerialName("drawnParticipant")
    val drawnParticipantPublicToken: String?,
    val isDrawingClosed: Boolean,
)