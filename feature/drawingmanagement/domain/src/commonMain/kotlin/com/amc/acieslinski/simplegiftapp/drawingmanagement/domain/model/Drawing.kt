package com.amc.acieslinski.simplegiftapp.drawingmanagement.domain.model

import kotlinx.datetime.Instant

data class Drawing(
    val id: String,
    val title: String,
    val description: String,
    val createdDate: Instant,
//    val participants: List<User>,
//    val drawnParticipant: User?,
//    val isDrawingClosed: Boolean,
)