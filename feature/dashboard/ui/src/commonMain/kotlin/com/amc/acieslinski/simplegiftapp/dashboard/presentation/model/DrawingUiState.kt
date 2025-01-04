package com.amc.acieslinski.simplegiftapp.dashboard.presentation.model

import com.amc.acieslinski.simplegiftapp.presentation.getShortFormattedDate
import kotlinx.datetime.Instant

data class DrawingUiState(
    val id: String,
    val orderNumber: Int,
    val title: String,
    val date: Instant,
)

fun DrawingUiState.getFormattedDate(): String = date.getShortFormattedDate()