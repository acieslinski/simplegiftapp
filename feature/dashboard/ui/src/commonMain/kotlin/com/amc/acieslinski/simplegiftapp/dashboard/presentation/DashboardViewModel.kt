package com.amc.acieslinski.simplegiftapp.dashboard.presentation

import com.amc.acieslinski.simplegiftapp.BaseViewModel
import com.amc.acieslinski.simplegiftapp.dashboard.domain.GetDrawingsUseCase
import com.amc.acieslinski.simplegiftapp.dashboard.domain.SelectDrawingUseCase
import com.amc.acieslinski.simplegiftapp.dashboard.presentation.mapper.DrawingMapper
import com.amc.acieslinski.simplegiftapp.dashboard.presentation.model.DashboardUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

abstract class DashboardViewModel(
) : BaseViewModel() {
    abstract val dashboardUiState: StateFlow<DashboardUiState>
    abstract fun onSelectDrawingAction(drawingId: String)
}

class DashboardViewModelImpl(
    private val getDrawingsUseCase: GetDrawingsUseCase,
    private val selectDrawingUseCase: SelectDrawingUseCase,
    private val drawingMapper: DrawingMapper
) : DashboardViewModel() {
    override val dashboardUiState = MutableStateFlow<DashboardUiState>(DashboardUiState.Loading)

    init {
        scope.launch {
            getDrawingsUseCase()
                .onEach {  drawings ->
                    dashboardUiState.update {
                        if (drawings.isEmpty()) {
                            DashboardUiState.Empty
                        } else {
                            DashboardUiState.Success(
                                drawings = drawings.map { drawingMapper.mapToUiState(it) }
                            )
                        }
                    }
                }
                .collect()
        }
    }

    override fun onSelectDrawingAction(drawingId: String) {
        selectDrawingUseCase(drawingId)
    }
}