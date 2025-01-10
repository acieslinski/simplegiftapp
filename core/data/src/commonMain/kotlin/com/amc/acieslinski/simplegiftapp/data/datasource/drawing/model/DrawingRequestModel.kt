package com.amc.acieslinski.simplegiftapp.data.datasource.drawing.model

import kotlinx.datetime.Instant
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

private const val INITIAL_TIME = 1704067200L // Timestamp for 2024-01-01T00:00:00Z in seconds

@Serializable
data class DrawingRequestModel(
    val id: String? = null,
    val title: String,
    val description: String,
    @SerialName("participants")
    val participantsTokens: List<String>,
//    @SerialName("drawnParticipant")
//    val drawnParticipantPublicToken: String?,
//    val isDrawingClosed: Boolean,
    val createdDate: Instant = Instant.fromEpochSeconds(INITIAL_TIME),
)