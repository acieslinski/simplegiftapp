package com.amc.acieslinski.simplegiftapp.dashboard.presentation

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlin.time.Duration.Companion.days

class FakeDashboardViewModel : DashboardViewModel() {
    override val dashboardUiState = MutableStateFlow(
        DashboardUiState(
            drawingsState = DrawingsUiState.Success(
                drawings = listOf(
                    DrawingUiState(
                        id = "test-id-1",
                        orderNumber = 1,
                        title = "title",
                        createdDate = Instant.fromEpochMilliseconds(
                            Clock.System.now().toEpochMilliseconds()
                        )
                    ),
                    DrawingUiState(
                        id = "test-id-2",
                        orderNumber = 2,
                        title = "title 2",
                        createdDate = Instant.fromEpochMilliseconds(
                            Clock.System.now().plus(5.days).toEpochMilliseconds()
                        )
                    ),
                )
            ),
            userPublicIdQrCodeUiState = UserPublicIdQrCodeUiState.HIDDEN,
            alertState = DashboardAlertState.HIDDEN,
        )
    )

    override fun onSelectDrawingAction(drawingId: String) {
        TODO("Not yet implemented")
    }

    override fun onShowUserPublicIdQrCodeAction() {
        TODO("Not yet implemented")
    }

    override fun onHideUserPublicIdQrCodeAction() {
        TODO("Not yet implemented")
    }

    override fun onAlertAckAction() {
        TODO("Not yet implemented")
    }
}