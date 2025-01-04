package com.amc.acieslinski.simplegiftapp.drawingmanagement.presentation

import com.amc.acieslinski.simplegiftapp.presentation.BaseViewModel
import com.amc.acieslinski.simplegiftapp.drawingmanagement.domain.GetSelectedDrawingUseCase
import com.amc.acieslinski.simplegiftapp.drawingmanagement.domain.GetUserUseCase
import com.amc.acieslinski.simplegiftapp.drawingmanagement.domain.model.SelectedDrawingResult
import com.amc.acieslinski.simplegiftapp.drawingmanagement.domain.model.User
import com.amc.acieslinski.simplegiftapp.drawingmanagement.domain.model.UserResult
import com.amc.acieslinski.simplegiftapp.presentation.getShortFormattedDate
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.datetime.Instant

abstract class DrawingViewModel(
) : BaseViewModel() {
    abstract val drawingUiState: StateFlow<DrawingUiState>

    abstract fun addParticipant(id: String)
}

class DrawingViewModelImpl(
    private val getUserUseCase: GetUserUseCase,
    private val getSelectedDrawingUseCase: GetSelectedDrawingUseCase,
) : DrawingViewModel() {
    private val _drawingUiState = MutableStateFlow(DrawingUiState())
    override val drawingUiState: StateFlow<DrawingUiState> = _drawingUiState

    init {
        scope.launch {
            when (val result = getSelectedDrawingUseCase()) {
                is SelectedDrawingResult.Success -> _drawingUiState.update {
                    it.copy(
                        id = result.drawing.id,
                        title = result.drawing.title,
                        details = result.drawing.description,
                        date = result.drawing.createdDate,
                        isLoading = false,
                    )
                }
                is SelectedDrawingResult.Empty -> _drawingUiState.update {
                    it.copy(
                        isLoading = false,
                        isDrawingNotAvailable = true
                    )
                }
            }
        }
    }

    override fun addParticipant(id: String) {
        getUserUseCase(id)
            .onEach {
                with(_drawingUiState.value) {
                    if (it is UserResult.Success) {
                        _drawingUiState.value =
                            copy(participants = participants + it.user.toUiState())
                    } else {
                        // TODO emit for dialog with error message
                    }
                }
            }
            .launchIn(scope)
    }

    private fun User.toUiState() = ParticipantUiState(
        name = name,
        surname = surname,
        id = idToken
    )
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
    val isLoading: Boolean = true,
    val isDrawingNotAvailable: Boolean = false,
    private val date: Instant? = null,
) {
    fun getFormattedDate() = date?.getShortFormattedDate() ?: ""
}