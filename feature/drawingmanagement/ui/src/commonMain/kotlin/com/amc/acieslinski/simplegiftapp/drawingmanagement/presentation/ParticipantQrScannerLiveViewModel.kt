package com.amc.acieslinski.simplegiftapp.drawingmanagement.presentation

import com.amc.acieslinski.simplegiftapp.drawingmanagement.domain.AddParticipantToSelectedDrawingUseCase
import com.amc.acieslinski.simplegiftapp.drawingmanagement.domain.model.AddParticipantResult
import com.amc.acieslinski.simplegiftapp.presentation.BaseViewModel
import com.amc.acieslinski.simplegiftapp.resources.Res
import com.amc.acieslinski.simplegiftapp.resources.drawing_management_add_participant_close
import com.amc.acieslinski.simplegiftapp.resources.drawing_management_add_participant_failure
import com.amc.acieslinski.simplegiftapp.resources.drawing_management_add_participant_success
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.StringResource

abstract class ParticipantQrScannerViewModel(
) : BaseViewModel() {
    abstract val participantQrScannerUiState: StateFlow<ParticipantQrScannerUiState>

    abstract fun onAddParticipantAction(userToken: String)
    abstract fun onAlertAckAction()
}

class ParticipantQrScannerLiveViewModel(
    private val addParticipantToSelectedDrawingUseCase: AddParticipantToSelectedDrawingUseCase,
) : ParticipantQrScannerViewModel() {
    private val uiState = MutableStateFlow(ParticipantQrScannerUiState())
    override val participantQrScannerUiState = uiState.asStateFlow()

    override fun onAddParticipantAction(userToken: String) {
        if (uiState.value.isReadyToAddParticipant()) {
            uiState.update { it.copy(isAdding = true) }
            scope.launch {
                val result = addParticipantToSelectedDrawingUseCase(userToken)
                uiState.update { result.toUiState() }
            }
        }
    }

    override fun onAlertAckAction() {
        uiState.update { ParticipantQrScannerUiState() }
    }
}

data class ParticipantQrScannerUiState(
    val isAdding: Boolean = false,
    val isAdded: Boolean = false,
    val isFailure: Boolean = false,
    val alertState: ParticipantQrScannerAlertState = ParticipantQrScannerAlertState()
) {
    fun isReadyToAddParticipant(): Boolean = !(isAdded || isFailure || isAdding)

    companion object {
        fun added() = ParticipantQrScannerUiState(
            isAdding = false,
            isAdded = true,
            alertState = ParticipantQrScannerAlertState.success()
        )

        fun failed() =
            ParticipantQrScannerUiState(
                isAdding = false,
                isAdded = false,
                isFailure = true,
                alertState = ParticipantQrScannerAlertState.failed()
            )
    }
}

fun AddParticipantResult.toUiState() = when (this) {
    is AddParticipantResult.Success -> ParticipantQrScannerUiState.added()
    is AddParticipantResult.UnknownFailure -> ParticipantQrScannerUiState.failed()
}

data class ParticipantQrScannerAlertState(
    val isHidden: Boolean = true,
    val messageRes: StringResource = Res.string.drawing_management_add_participant_success,
    val closeLabelRes: StringResource = Res.string.drawing_management_add_participant_close,
) {
    companion object {
        fun failed() = ParticipantQrScannerAlertState(
            isHidden = false,
            messageRes = Res.string.drawing_management_add_participant_failure,
        )

        fun success() = ParticipantQrScannerAlertState(
            isHidden = false,
            messageRes = Res.string.drawing_management_add_participant_success,
        )
    }
}