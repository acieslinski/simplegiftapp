package com.amc.acieslinski.simplegiftapp.drawingmanagement.presentation

import com.amc.acieslinski.simplegiftapp.presentation.BaseViewModel
import com.amc.acieslinski.simplegiftapp.drawingmanagement.domain.CreateDrawingUseCase
import com.amc.acieslinski.simplegiftapp.drawingmanagement.presentation.model.NewDrawingAlertState
import com.amc.acieslinski.simplegiftapp.drawingmanagement.presentation.model.NewDrawingUiState
import com.amc.acieslinski.simplegiftapp.drawingmanagement.presentation.model.SaveDrawingError
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

abstract class NewDrawingViewModel(
) : BaseViewModel() {
    abstract val newDrawingUiState: StateFlow<NewDrawingUiState>
    abstract val newDrawingAlertState: StateFlow<NewDrawingAlertState>

    abstract fun onTitleChanged(title: String)

    abstract fun onDescriptionChanged(description: String)

    abstract fun onCreateDrawingAction()

    abstract fun onAlertAck()

    abstract fun onCancelAction()
}

class NewDrawingViewModelImpl (
    private val createDrawingUseCase: CreateDrawingUseCase
) : NewDrawingViewModel() {
    private val _newDrawingUiState = MutableStateFlow(NewDrawingUiState())
    override val newDrawingUiState: StateFlow<NewDrawingUiState> = _newDrawingUiState

    private val _newDrawingAlertState = MutableStateFlow<NewDrawingAlertState>(
        NewDrawingAlertState.Hidden)
    override val newDrawingAlertState: StateFlow<NewDrawingAlertState> = _newDrawingAlertState

    override fun onTitleChanged(title: String) {
        _newDrawingUiState.update { it.copy(title = title) }
    }

    override fun onDescriptionChanged(description: String) {
        _newDrawingUiState.update { it.copy(description = description) }
    }

    override fun onCreateDrawingAction() {
        scope.launch {
            _newDrawingUiState.value.toDomain().let { drawing ->
                val result = createDrawingUseCase(drawing)
                _newDrawingUiState.update {
                    it.copy(
                        isSaved = result.isSuccess,
                        isError = result.isFailure
                    )
                }
                result.onFailure {
                    _newDrawingAlertState.update {
                        // TODO error mapper
                        NewDrawingAlertState.SaveError(SaveDrawingError.Unknown)
                    }
                }
                result.onSuccess {
                    _newDrawingAlertState.update { NewDrawingAlertState.Confirmation }
                }
            }
        }
    }

    override fun onAlertAck() {
        _newDrawingUiState.update { it.copy(isSaveAck = true) }
        _newDrawingAlertState.update { NewDrawingAlertState.Hidden }
    }

    override fun onCancelAction() {
        _newDrawingUiState.update { it.copy(isCancelled = true) }
    }
}