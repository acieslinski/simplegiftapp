package com.amc.acieslinski.simplegiftapp.drawingmanagement.presentation

import com.amc.acieslinski.simplegiftapp.drawingmanagement.domain.AddParticipantToSelectedDrawingUseCase
import com.amc.acieslinski.simplegiftapp.drawingmanagement.domain.IsParticipantAddedToSelectedDrawingUseCase
import com.amc.acieslinski.simplegiftapp.drawingmanagement.domain.model.AddParticipantResult
import com.amc.acieslinski.simplegiftapp.presentation.BaseViewModel
import com.amc.acieslinski.simplegiftapp.resources.Res
import com.amc.acieslinski.simplegiftapp.resources.drawing_management_add_participant_close
import com.amc.acieslinski.simplegiftapp.resources.drawing_management_add_participant_failure_already_added
import com.amc.acieslinski.simplegiftapp.resources.drawing_management_add_participant_failure_unknown
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
    private val isParticipantAddedToSelectedDrawingUseCase: IsParticipantAddedToSelectedDrawingUseCase,
) : ParticipantQrScannerViewModel() {
    private val uiState = MutableStateFlow(ParticipantQrScannerUiState())
    override val participantQrScannerUiState = uiState.asStateFlow()

    override fun onAddParticipantAction(userToken: String) {
        if (uiState.value.isReadyToAddParticipant()) {
            uiState.update { it.copy(isAdding = true) }
            scope.launch {
                if (isParticipantAddedToSelectedDrawingUseCase(userToken)) {
                    uiState.update { ParticipantQrScannerUiState.alreadyAddedFailure() }
                } else {
                    val result = addParticipantToSelectedDrawingUseCase(userToken)
                    uiState.update { result.toUiState() }
                }
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
        fun success() = ParticipantQrScannerUiState(
            isAdding = false,
            isAdded = true,
            alertState = ParticipantQrScannerAlertState.success()
        )

        fun unknownFailure() =
            ParticipantQrScannerUiState(
                isAdding = false,
                isAdded = false,
                isFailure = true,
                alertState = ParticipantQrScannerAlertState.unknownFailure()
            )

        fun alreadyAddedFailure() =
            ParticipantQrScannerUiState(
                isAdding = false,
                isAdded = true,
                isFailure = true,
                alertState = ParticipantQrScannerAlertState.participantAlreadyAdded()
            )
    }
}

fun AddParticipantResult.toUiState() = when (this) {
    is AddParticipantResult.Success -> ParticipantQrScannerUiState.success()
    is AddParticipantResult.UnknownFailure -> ParticipantQrScannerUiState.unknownFailure()
}

data class ParticipantQrScannerAlertState(
    val isHidden: Boolean = true,
    val messageRes: StringResource = Res.string.drawing_management_add_participant_success,
    val closeLabelRes: StringResource = Res.string.drawing_management_add_participant_close,
) {
    companion object {
        fun unknownFailure() = ParticipantQrScannerAlertState(
            isHidden = false,
            messageRes = Res.string.drawing_management_add_participant_failure_unknown,
        )

        fun participantAlreadyAdded() = ParticipantQrScannerAlertState(
            isHidden = false,
            messageRes = Res.string.drawing_management_add_participant_failure_already_added,
        )

        fun success() = ParticipantQrScannerAlertState(
            isHidden = false,
            messageRes = Res.string.drawing_management_add_participant_success,
        )
    }
}