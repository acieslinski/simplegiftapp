package com.amc.acieslinski.simplegiftapp.drawingmanagement.presentation

import com.amc.acieslinski.simplegiftapp.presentation.BaseViewModel
import com.amc.acieslinski.simplegiftapp.drawingmanagement.domain.ObserveSelectedDrawingUseCase
import com.amc.acieslinski.simplegiftapp.drawingmanagement.domain.model.Drawing
import com.amc.acieslinski.simplegiftapp.drawingmanagement.domain.model.DrawingParticipant
import com.amc.acieslinski.simplegiftapp.drawingmanagement.domain.model.SelectedDrawingUpdate
import com.amc.acieslinski.simplegiftapp.presentation.getShortFormattedDate
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.datetime.Instant

abstract class DrawingViewModel(
) : BaseViewModel() {
    abstract val drawingUiState: StateFlow<DrawingUiState>
}

class DrawingLiveViewModel(
    private val observeSelectedDrawingUseCase: ObserveSelectedDrawingUseCase,
) : DrawingViewModel() {
    private val _drawingUiState = MutableStateFlow(DrawingUiState())
    override val drawingUiState: StateFlow<DrawingUiState> = observeSelectedDrawingUseCase()
        .map {
            when (it) {
                is SelectedDrawingUpdate.Success -> it.drawing.toUiState()
                is SelectedDrawingUpdate.UnknownFailure -> DrawingUiState(isError = true)
            }
        }
        .stateIn(scope, SharingStarted.WhileSubscribed(), DrawingUiState.LOADING)
}

data class ParticipantUiState(
    val name: String,
    val surname: String,
    val id: String
)

data class DrawingUiState(
    val id: String = "",
    val title: String = "",
    val details: String = "",
    val participants: List<ParticipantUiState> = emptyList(),
    val isLoading: Boolean = false,
    val isDrawingNotAvailable: Boolean = false,
    val isError: Boolean = false,
    private val date: Instant? = null,
) {
    fun getFormattedDate() = date?.getShortFormattedDate() ?: ""

    companion object {
        val LOADING = DrawingUiState(isLoading = true)
    }
}

fun DrawingParticipant.toUiState() = ParticipantUiState(
    id = id,
    name = name,
    surname = surname,
)

fun Drawing.toUiState() = DrawingUiState(
    id = id,
    title = title,
    details = description,
    participants = participants.map { it.toUiState() },
    isLoading = false,
    date = createdDate,
)