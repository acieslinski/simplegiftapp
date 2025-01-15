package com.amc.acieslinski.simplegiftapp.data.datasource.drawing.model

import kotlinx.datetime.Instant
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

private val DEFAULT_CREATED_DATE = Instant.parse("2024-01-01T00:00:00Z")

@Serializable
data class DrawingResponseModel(
    val id: String,
    val title: String,
    val description: String,
    @SerialName("participants")
    val participantsIds: List<String>,
    @SerialName("drawnParticipant")
    val drawnParticipantId: String?,
    val isLobbyClosed: Boolean,
    val createdDate: Instant = DEFAULT_CREATED_DATE,
)