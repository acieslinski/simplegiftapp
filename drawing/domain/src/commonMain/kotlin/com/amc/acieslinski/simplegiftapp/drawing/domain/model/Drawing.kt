package com.amc.acieslinski.simplegiftapp.drawing.domain.model

data class Drawing(
    val title: String,
    val description: String,
    val participants: List<User>,
    val drawnParticipant: User?,
    val isDrawingClosed: Boolean
)