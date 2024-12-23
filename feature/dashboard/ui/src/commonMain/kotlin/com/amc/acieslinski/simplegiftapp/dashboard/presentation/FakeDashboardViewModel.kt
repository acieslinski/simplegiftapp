package com.amc.acieslinski.simplegiftapp.dashboard.presentation

import com.amc.acieslinski.simplegiftapp.dashboard.presentation.model.DashboardUiState
import com.amc.acieslinski.simplegiftapp.dashboard.presentation.model.DrawingUiState
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
                    date = Instant.fromEpochMilliseconds(Clock.System.now().toEpochMilliseconds())
                ),
                DrawingUiState(
                    id = "test-id-2",
                    orderNumber = 2,
                    title = "title 2",
                    date = Instant.fromEpochMilliseconds(Clock.System.now().plus(5.days).toEpochMilliseconds())
                ),
            )
        )
    )

    override fun onSelectDrawingAction(drawingId: String) {
        // do nothing
    }
}