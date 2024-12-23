package com.amc.acieslinski.simplegiftapp.dashboard.presentation.model

sealed class DashboardUiState {
    data object Empty : DashboardUiState()

    data object Loading : DashboardUiState()

    data class Success(
        val drawings: List<DrawingUiState>
    ) : DashboardUiState()
}