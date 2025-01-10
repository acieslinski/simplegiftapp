package com.amc.acieslinski.simplegiftapp.dashboard.presentation

import com.amc.acieslinski.simplegiftapp.presentation.BaseViewModel
import com.amc.acieslinski.simplegiftapp.dashboard.domain.GetDrawingsUseCase
import com.amc.acieslinski.simplegiftapp.dashboard.domain.SelectDrawingUseCase
import com.amc.acieslinski.simplegiftapp.dashboard.domain.model.Drawing
import com.amc.acieslinski.simplegiftapp.dashboard.domain.model.DrawingsResult
import com.amc.acieslinski.simplegiftapp.presentation.getShortFormattedDate
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.datetime.Instant

abstract class DashboardViewModel(
) : BaseViewModel() {
    abstract val dashboardUiState: StateFlow<DashboardUiState>
    abstract fun onSelectDrawingAction(drawingId: String)
}

class DashboardViewModelImpl(
    private val getDrawingsUseCase: GetDrawingsUseCase,
    private val selectDrawingUseCase: SelectDrawingUseCase,
) : DashboardViewModel() {
    private val _dashboardUiState = flow {
            when (val result = getDrawingsUseCase()) {
                is DrawingsResult.Success -> result.drawings.toUiState()
                is DrawingsResult.UnknownFailure -> DashboardUiState.DrawingsListFailure
            }.run {
                emit(this)
            }
        }.stateIn(scope, SharingStarted.WhileSubscribed(), DashboardUiState.Empty)
    override val dashboardUiState = _dashboardUiState

    override fun onSelectDrawingAction(drawingId: String) {
        scope.launch {
            selectDrawingUseCase(drawingId)
        }
    }
}

data class DrawingUiState(
    val id: String,
    val orderNumber: Int,
    val title: String,
    val createdDate: Instant,
)

fun DrawingUiState.getFormattedDate(): String = createdDate.getShortFormattedDate()

sealed interface DashboardUiState {
    data object Empty : DashboardUiState

    data object Loading : DashboardUiState

    data class Success(
        val drawings: List<DrawingUiState>
    ) : DashboardUiState

    sealed interface Failure: DashboardUiState

    data object DrawingsListFailure: Failure
}

fun Drawing.toUiState() = DrawingUiState(
    id = id,
    orderNumber = orderNumber,
    title = title,
    createdDate = createdDate,
)

fun List<Drawing>.toUiState() =
    map { it.toUiState() }.
    let {
        if (isNotEmpty()) {
            DashboardUiState.Success(drawings = it)
        } else {
            DashboardUiState.Empty
        }
    }