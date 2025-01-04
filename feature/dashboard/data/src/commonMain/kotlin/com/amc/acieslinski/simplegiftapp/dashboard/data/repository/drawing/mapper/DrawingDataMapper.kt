package com.amc.acieslinski.simplegiftapp.dashboard.data.repository.drawing.mapper

import com.amc.acieslinski.simplegiftapp.data.datasource.drawing.model.DrawingRemoteModel
import com.amc.acieslinski.simplegiftapp.data.datasource.drawing.model.GetDrawingsRequestModel
import com.amc.acieslinski.simplegiftapp.dashboard.domain.model.Drawing
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlin.time.Duration.Companion.days

private const val INITIAL_TIME = 1704067200L // Timestamp for 2024-01-01T00:00:00Z in seconds

class DrawingDataMapper {
    fun map(privateToken: String) = GetDrawingsRequestModel(
            privateToken = privateToken,
        )

    fun resolve(indexNumber: Int, remote: DrawingRemoteModel) = Drawing(
        id = remote.id,
        orderNumber = indexNumber + 1,
        title = remote.title,
        date = remote.createdDate ?: Instant.fromEpochSeconds(INITIAL_TIME)
    )
}