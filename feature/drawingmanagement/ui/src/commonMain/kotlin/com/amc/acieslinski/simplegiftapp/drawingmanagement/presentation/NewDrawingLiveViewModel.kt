package com.amc.acieslinski.simplegiftapp.drawingmanagement.presentation

import com.amc.acieslinski.simplegiftapp.presentation.BaseViewModel
import com.amc.acieslinski.simplegiftapp.drawingmanagement.domain.CreateDrawingUseCase
import com.amc.acieslinski.simplegiftapp.drawingmanagement.domain.model.CreateDrawingResult
import com.amc.acieslinski.simplegiftapp.drawingmanagement.domain.model.NewDrawing
import com.amc.acieslinski.simplegiftapp.resources.Res
import com.amc.acieslinski.simplegiftapp.resources.drawing_created_failure
import com.amc.acieslinski.simplegiftapp.resources.drawing_created_success
import com.amc.acieslinski.simplegiftapp.resources.drawing_creation_alert_close
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.StringResource

abstract class NewDrawingViewModel(
) : BaseViewModel() {
    abstract val newDrawingUiState: StateFlow<NewDrawingUiState>
    abstract val newDrawingAlertState: StateFlow<NewDrawingAlertState>

    abstract fun onTitleChanged(title: String)

    abstract fun onDescriptionChanged(description: String)

    abstract fun onCreateDrawingAction()

    abstract fun onAlertAckAction()
}

class NewDrawingLiveViewModel(
    private val createDrawingUseCase: CreateDrawingUseCase
) : NewDrawingViewModel() {
    private val _newDrawingUiState = MutableStateFlow(NewDrawingUiState())
    override val newDrawingUiState: StateFlow<NewDrawingUiState> = _newDrawingUiState

    private val _newDrawingAlertState = MutableStateFlow<NewDrawingAlertState>(
        NewDrawingAlertState.Hidden
    )
    override val newDrawingAlertState: StateFlow<NewDrawingAlertState> = _newDrawingAlertState

    override fun onTitleChanged(title: String) {
        _newDrawingUiState.update { it.copy(title = title) }
    }

    override fun onDescriptionChanged(description: String) {
        _newDrawingUiState.update { it.copy(description = description) }
    }

    override fun onCreateDrawingAction() {
        _newDrawingUiState.update { it.saving() }
        scope.launch {
            val drawing = _newDrawingUiState.value.toDomain()
            when (createDrawingUseCase(drawing)) {
                is CreateDrawingResult.Success -> {
                    _newDrawingUiState.update { it.saved() }
                    _newDrawingAlertState.update {
                        NewDrawingAlertState.DrawingCreationConfirmation
                    }
                }
                is CreateDrawingResult.UnknownFailure -> {
                    _newDrawingUiState.update { it.error() }
                    _newDrawingAlertState.update {
                        NewDrawingAlertState.DrawingCreationUnknownFailure
                    }
                }
            }
        }
    }

    override fun onAlertAckAction() {
        _newDrawingAlertState.update { NewDrawingAlertState.Hidden }
    }
}

sealed class NewDrawingAlertState {
    val closeLabelRes: StringResource = Res.string.drawing_creation_alert_close
    open val messageRes: StringResource = Res.string.drawing_created_success

    data object DrawingCreationConfirmation : NewDrawingAlertState()

    data object Hidden : NewDrawingAlertState()

    sealed class Failure(override val messageRes: StringResource): NewDrawingAlertState()

    data object DrawingCreationUnknownFailure: Failure(Res.string.drawing_created_failure)
}

data class NewDrawingUiState(
    val title: String = "",
    val description: String = "",
    val isSaved: Boolean = false,
    val isError: Boolean = false,
    val isSaving: Boolean = false,
) {
    fun saved() = copy(isSaved = true, isSaving = false)

    fun saving() = copy(isSaving = true)

    fun error() = copy(isError = true, isSaving = false)
}

fun NewDrawingUiState.toDomain() = NewDrawing(
    title = title,
    description = description,
)