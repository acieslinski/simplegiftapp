package com.amc.acieslinski.simplegiftapp.dashboard.domain.model

import kotlinx.datetime.Instant

data class Drawing(
    val id: String,
    val orderNumber: Int,
    val title: String,
    val createdDate: Instant,
)