package com.amc.acieslinski.simplegiftapp.dashboard.presentation

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlin.time.Duration.Companion.days

class FakeDashboardViewModel : DashboardViewModel() {
    override val dashboardUiState = MutableStateFlow(
        DashboardUiState.Success(
            drawings = listOf(
                DrawingUiState(
                    id = "test-id-1",
                    orderNumber = 1,
                    title = "title",
                    createdDate = Instant.fromEpochMilliseconds(Clock.System.now().toEpochMilliseconds())
                ),
                DrawingUiState(
                    id = "test-id-2",
                    orderNumber = 2,
                    title = "title 2",
                    createdDate = Instant.fromEpochMilliseconds(Clock.System.now().plus(5.days).toEpochMilliseconds())
                ),
            )
        )
    )

    override fun onSelectDrawingAction(drawingId: String) {
        // do nothing
    }
}