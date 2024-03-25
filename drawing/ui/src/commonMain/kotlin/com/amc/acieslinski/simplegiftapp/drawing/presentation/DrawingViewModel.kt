package com.amc.acieslinski.simplegiftapp.drawing.presentation

import com.amc.acieslinski.simplegiftapp.BaseViewModel
import com.amc.acieslinski.simplegiftapp.drawing.domain.GetUserUseCase
import com.amc.acieslinski.simplegiftapp.drawing.domain.model.User
import com.amc.acieslinski.simplegiftapp.drawing.domain.model.UserResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

abstract class DrawingViewModel(
) : BaseViewModel() {
    abstract val drawingUiState: StateFlow<DrawingUiState>

    abstract fun addParticipant(id: String)
}

class DrawingViewModelImpl(
    private val getUserUseCase: GetUserUseCase
) : DrawingViewModel() {
    private val _drawingUiState = MutableStateFlow(DrawingUiState())
    override val drawingUiState: StateFlow<DrawingUiState> = _drawingUiState

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
    val participants: List<ParticipantUiState> = emptyList()
)