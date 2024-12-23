package com.amc.acieslinski.simplegiftapp.dashboard.presentation.model

import kotlinx.datetime.Instant

data class DrawingUiState(
    val id: String,
    val orderNumber: Int,
    val title: String,
    val date: Instant,
)

expect fun DrawingUiState.getFormattedDate(): String