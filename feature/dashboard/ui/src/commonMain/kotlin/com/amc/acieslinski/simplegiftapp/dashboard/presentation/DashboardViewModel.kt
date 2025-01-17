package com.amc.acieslinski.simplegiftapp.dashboard.presentation

import com.amc.acieslinski.simplegiftapp.dashboard.domain.GetCurrentUserPublicQrCode
import com.amc.acieslinski.simplegiftapp.presentation.BaseViewModel
import com.amc.acieslinski.simplegiftapp.dashboard.domain.GetDrawingsUseCase
import com.amc.acieslinski.simplegiftapp.dashboard.domain.SelectDrawingUseCase
import com.amc.acieslinski.simplegiftapp.dashboard.domain.model.Drawing
import com.amc.acieslinski.simplegiftapp.dashboard.domain.model.DrawingsResult
import com.amc.acieslinski.simplegiftapp.dashboard.domain.model.PublicQrCodeResult
import com.amc.acieslinski.simplegiftapp.presentation.getShortFormattedDate
import com.amc.acieslinski.simplegiftapp.resources.Res
import com.amc.acieslinski.simplegiftapp.resources.dashboard_drawings_failure_close
import com.amc.acieslinski.simplegiftapp.resources.dashboard_drawings_failure_unknown
import com.amc.acieslinski.simplegiftapp.resources.dashboard_qrcode_failure_close
import com.amc.acieslinski.simplegiftapp.resources.dashboard_qrcode_failure_unknown
import com.amc.acieslinski.simplegiftapp.resources.dashboard_qrcode_hint
import com.amc.acieslinski.simplegiftapp.resources.drawing_management_draw_participant_unavailable_close
import com.amc.acieslinski.simplegiftapp.resources.drawing_management_draw_participant_unavailable_message
import com.amc.acieslinski.simplegiftapp.resources.drawing_management_error_close
import com.amc.acieslinski.simplegiftapp.resources.drawing_management_error_unknown
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.datetime.Instant
import org.jetbrains.compose.resources.StringResource
import kotlin.contracts.contract
import kotlin.jvm.Transient

abstract class DashboardViewModel(
) : BaseViewModel() {
    abstract val dashboardUiState: StateFlow<DashboardUiState>
    abstract fun onSelectDrawingAction(drawingId: String)

    abstract fun onShowUserPublicIdQrCodeAction()

    abstract fun onHideUserPublicIdQrCodeAction()

    abstract fun onAlertAckAction()
}

class DashboardViewModelImpl(
    private val getDrawingsUseCase: GetDrawingsUseCase,
    private val selectDrawingUseCase: SelectDrawingUseCase,
    private val getCurrentUserPublicQrCode: GetCurrentUserPublicQrCode,
) : DashboardViewModel() {
    private val localDashboardUiState = MutableStateFlow(DashboardUiState.INITIAL)

    @OptIn(ExperimentalCoroutinesApi::class)
    override val dashboardUiState = flow {
        emit(getDrawingsUseCase())
    }
        .onEach { drawingResult ->
            with(localDashboardUiState) {
                when (drawingResult) {
                    is DrawingsResult.Success -> {
                        update { it.success(drawingResult.drawings.toUiState()) }
                    }

                    is DrawingsResult.UnknownFailure -> update { it.drawingsFailure() }
                }
            }
        }
        .flatMapLatest { localDashboardUiState }
        .stateIn(scope, SharingStarted.WhileSubscribed(), DashboardUiState.INITIAL)

    override fun onSelectDrawingAction(drawingId: String) {
        scope.launch {
            selectDrawingUseCase(drawingId)
        }
    }

    override fun onShowUserPublicIdQrCodeAction() {
        if (!localDashboardUiState.value.userPublicIdQrCodeUiState.isLoading) {
            localDashboardUiState.value = localDashboardUiState.value.loadingUserPublicIdQrCode()
            scope.launch {
                val result = getCurrentUserPublicQrCode()
                with(localDashboardUiState) {
                    when (result) {
                        is PublicQrCodeResult.Success -> update {
                            it.success(
                                userPublicId = result.publicId,
                                qrCode = result.qrCodeBytes
                            )
                        }

                        is PublicQrCodeResult.UnknownFailure -> update { it.userPublicUserIdFailure() }
                    }
                }
            }
        }
    }

    override fun onHideUserPublicIdQrCodeAction() {
        localDashboardUiState.update {
            it.hiddenUserPublicIdQrCode()
        }
    }

    override fun onAlertAckAction() {
        localDashboardUiState.update {
            it.alertAck()
        }
    }
}

@Suppress("ArrayInDataClass")
data class UserPublicIdQrCodeUiState(
    val isVisible: Boolean,
    val isLoading: Boolean,
    private val userPublicId: String,
    @Transient val qrCode: ByteArray,
    val hintRes: StringResource = Res.string.dashboard_qrcode_hint,
) {
    companion object {
        val HIDDEN = UserPublicIdQrCodeUiState(
            isVisible = false,
            isLoading = false,
            userPublicId = "",
            qrCode = ByteArray(0),
        )
    }
}

data class DashboardAlertState(
    val isVisible: Boolean,
    val messageRes: StringResource? = null,
    val closeLabelRes: StringResource? = null,
) {
    fun drawingsFailure() = copy(
        isVisible = true,
        messageRes = Res.string.dashboard_drawings_failure_unknown,
        closeLabelRes = Res.string.dashboard_drawings_failure_close
    )

    fun userPublicIdQrCodeFailure() = copy(
        isVisible = true,
        messageRes = Res.string.dashboard_qrcode_failure_unknown,
        closeLabelRes = Res.string.dashboard_qrcode_failure_close,
    )

    fun requireMessageRes() = requireNotNull(messageRes)

    fun requireCloseLabelRes() = requireNotNull(closeLabelRes)

    companion object {
        val HIDDEN = DashboardAlertState(isVisible = false)
    }
}

data class DrawingUiState(
    val id: String,
    val orderNumber: Int,
    val title: String,
    val createdDate: Instant,
)

fun DrawingUiState.getFormattedDate(): String = createdDate.getShortFormattedDate()

sealed interface DrawingsUiState {
    data object Empty : DrawingsUiState

    data object Loading : DrawingsUiState

    data class Success(
        val drawings: List<DrawingUiState>
    ) : DrawingsUiState
}

fun Drawing.toUiState() = DrawingUiState(
    id = id,
    orderNumber = orderNumber,
    title = title,
    createdDate = createdDate,
)

fun List<Drawing>.toUiState() = map { it.toUiState() }

data class DashboardUiState(
    val drawingsState: DrawingsUiState,
    val userPublicIdQrCodeUiState: UserPublicIdQrCodeUiState,
    val alertState: DashboardAlertState,
) {
    fun success(drawings: List<DrawingUiState>) = copy(
        drawingsState = DrawingsUiState.Success(drawings)
    )

    fun success(userPublicId: String, qrCode: ByteArray) = copy(
        userPublicIdQrCodeUiState = userPublicIdQrCodeUiState.copy(
            isVisible = true,
            isLoading = false,
            userPublicId = userPublicId,
            qrCode = qrCode,
        )
    )

    fun drawingsFailure() = copy(
        alertState = alertState.drawingsFailure(),
    )

    fun userPublicUserIdFailure() = copy(
        alertState = alertState.userPublicIdQrCodeFailure()
    )

    fun loadingUserPublicIdQrCode() = copy(
        userPublicIdQrCodeUiState = userPublicIdQrCodeUiState.copy(
            isLoading = true
        )
    )

    fun hiddenUserPublicIdQrCode() = copy(
        userPublicIdQrCodeUiState = userPublicIdQrCodeUiState.copy(
            isVisible = false
        )
    )

    fun alertAck() = copy(
        alertState = DashboardAlertState.HIDDEN
    )

    companion object {
        val INITIAL = DashboardUiState(
            drawingsState = DrawingsUiState.Empty,
            userPublicIdQrCodeUiState = UserPublicIdQrCodeUiState.HIDDEN,
            alertState = DashboardAlertState.HIDDEN,
        )
    }
}