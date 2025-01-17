package com.amc.acieslinski.simplegiftapp.drawingmanagement.presentation

import com.amc.acieslinski.simplegiftapp.drawingmanagement.domain.CloseSelectedDrawingUseCase
import com.amc.acieslinski.simplegiftapp.presentation.BaseViewModel
import com.amc.acieslinski.simplegiftapp.drawingmanagement.domain.ObserveSelectedDrawingUseCase
import com.amc.acieslinski.simplegiftapp.drawingmanagement.domain.model.Drawing
import com.amc.acieslinski.simplegiftapp.drawingmanagement.domain.model.DrawingParticipant
import com.amc.acieslinski.simplegiftapp.drawingmanagement.domain.model.SelectedDrawingUpdate
import com.amc.acieslinski.simplegiftapp.presentation.getShortFormattedDate
import com.amc.acieslinski.simplegiftapp.resources.Res
import com.amc.acieslinski.simplegiftapp.resources.drawing_management_draw_participant_unavailable_close
import com.amc.acieslinski.simplegiftapp.resources.drawing_management_draw_participant_unavailable_message
import com.amc.acieslinski.simplegiftapp.resources.drawing_management_error_close
import com.amc.acieslinski.simplegiftapp.resources.drawing_management_error_unknown
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.datetime.Instant
import org.jetbrains.compose.resources.StringResource

abstract class DrawingViewModel(
) : BaseViewModel() {
    abstract val drawingUiState: StateFlow<DrawingUiState>

    abstract fun onCloseDrawingAction()

    abstract fun onDrawParticipantAction()

    abstract fun onAlertAckAction()
}

class DrawingLiveViewModel(
    private val observeSelectedDrawingUseCase: ObserveSelectedDrawingUseCase,
    private val closeSelectedDrawingUseCase: CloseSelectedDrawingUseCase,
) : DrawingViewModel() {
    private val localDrawingUiState = MutableStateFlow(DrawingUiState.INITIAL)

    @OptIn(ExperimentalCoroutinesApi::class)
    override val drawingUiState: StateFlow<DrawingUiState> = observeSelectedDrawingUseCase()
        .onEach {
            with(localDrawingUiState.value) {
                when (it) {
                    is SelectedDrawingUpdate.Success -> success(it.drawing)
                    is SelectedDrawingUpdate.UnknownFailure -> unknownFailure()
                }.also {
                    localDrawingUiState.value = it
                }
            }
        }
        .flatMapLatest { localDrawingUiState }
        .stateIn(scope, SharingStarted.WhileSubscribed(), DrawingUiState.INITIAL)

    override fun onCloseDrawingAction() {
        scope.launch {
            closeSelectedDrawingUseCase()
        }
    }

    override fun onDrawParticipantAction() {
        if (drawingUiState.value.isDrawingAvailable) {
            localDrawingUiState.update {
                drawingUiState.value.copy(
                    drawingAlertState = DrawingAlertState.DrawnParticipantNotAvailable
                )
            }
        }
    }

    override fun onAlertAckAction() {
        localDrawingUiState.update {
            drawingUiState.value.copy(
                drawingAlertState = DrawingAlertState.Hidden
            )
        }
    }
}

// TODO unit tests

data class ParticipantUiState(
    val name: String,
    val surname: String,
    val id: String
)

sealed interface DrawingAlertState {
    sealed class Visible(
        val messageRes: StringResource,
        val closeLabelRes: StringResource,
    ) : DrawingAlertState

    data object DrawnParticipantNotAvailable : Visible(
        messageRes = Res.string.drawing_management_draw_participant_unavailable_message,
        closeLabelRes = Res.string.drawing_management_draw_participant_unavailable_close,
    )

    data object UnknownFailure : Visible(
        messageRes = Res.string.drawing_management_error_unknown,
        closeLabelRes = Res.string.drawing_management_error_close,
    )

    data object Hidden : DrawingAlertState
}

data class DrawingUiState(
    val id: String = "",
    val title: String = "",
    val details: String = "",
    val participants: List<ParticipantUiState> = emptyList(),
    val drawnParticipant: ParticipantUiState? = null,
    val isLoading: Boolean = false,
    val isFailure: Boolean = false,
    val isDrawingAvailable: Boolean = false,
    val isAddingParticipantAvailable: Boolean = false,
    val isCloseDrawingAvailable: Boolean = false,
    val drawingAlertState: DrawingAlertState = DrawingAlertState.Hidden,
    private val date: Instant? = null,
) {
    fun getFormattedDate() = date?.getShortFormattedDate() ?: ""

    fun unknownFailure() = copy(
        isLoading = false,
        isFailure = true,
        drawingAlertState = if (drawingAlertState is DrawingAlertState.Hidden) {
            DrawingAlertState.UnknownFailure
        } else {
            drawingAlertState
        }
    )

    fun success(drawing: Drawing) = with(drawing) {
        copy(
            id = id,
            title = title,
            details = description,
            participants = participants.map { it.toUiState() },
            drawnParticipant = drawnParticipant?.toUiState(),
            isLoading = false,
            isFailure = false,
            isAddingParticipantAvailable = !isLobbyClosed,
            isCloseDrawingAvailable = !isLobbyClosed,
            isDrawingAvailable = drawnParticipant == null,
            date = createdDate,
        )
    }

    companion object {
        val INITIAL = DrawingUiState(isLoading = true)
    }
}

fun DrawingParticipant.toUiState() = ParticipantUiState(
    id = id,
    name = name,
    surname = surname,
)