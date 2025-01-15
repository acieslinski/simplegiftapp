package com.amc.acieslinski.simplegiftapp.drawingmanagement.data.repository.drawing

import com.amc.acieslinski.simplegiftapp.data.datasource.account.AccountLocalDataSource
import com.amc.acieslinski.simplegiftapp.data.datasource.drawing.DrawingRemoteDataSource
import com.amc.acieslinski.simplegiftapp.data.datasource.drawing.SelectedDrawingIdLocalDataSource
import com.amc.acieslinski.simplegiftapp.data.datasource.drawing.model.DrawingRequestModel
import com.amc.acieslinski.simplegiftapp.data.datasource.drawing.model.DrawingResponseModel
import com.amc.acieslinski.simplegiftapp.drawingmanagement.domain.model.NewDrawing
import com.amc.acieslinski.simplegiftapp.drawingmanagement.domain.repositories.DrawingRepository
import com.amc.acieslinski.simplegiftapp.data.repository.Repository
import com.amc.acieslinski.simplegiftapp.drawingmanagement.data.datasource.drawing.DrawingDrawRemoteDataSource
import com.amc.acieslinski.simplegiftapp.drawingmanagement.data.datasource.user.UserRemoteDataSource
import com.amc.acieslinski.simplegiftapp.drawingmanagement.data.datasource.user.model.UserResponseModel
import com.amc.acieslinski.simplegiftapp.drawingmanagement.domain.model.AddParticipantResult
import com.amc.acieslinski.simplegiftapp.drawingmanagement.domain.model.CloseDrawingLobbyResult
import com.amc.acieslinski.simplegiftapp.drawingmanagement.domain.model.CreateDrawingResult
import com.amc.acieslinski.simplegiftapp.drawingmanagement.domain.model.DrawParticipantsResult
import com.amc.acieslinski.simplegiftapp.drawingmanagement.domain.model.Drawing
import com.amc.acieslinski.simplegiftapp.drawingmanagement.domain.model.SelectedDrawingUpdate
import com.amc.acieslinski.simplegiftapp.drawingmanagement.domain.model.DrawingParticipant
import com.amc.acieslinski.simplegiftapp.drawingmanagement.domain.model.SelectedDrawingIdResult
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.datetime.Clock

class DrawingLiveRepository(
    private val drawingRemoteDataSource: DrawingRemoteDataSource,
    private val drawingDrawRemoteDataSource: DrawingDrawRemoteDataSource,
    private val selectedDrawingIdLocalDataSource: SelectedDrawingIdLocalDataSource,
    private val userRemoteDataSource: UserRemoteDataSource,
    private val accountLocalDataSource: AccountLocalDataSource,
) : DrawingRepository, Repository() {
    private val updateTickFlow = MutableSharedFlow<Unit>()

    override suspend fun createDrawing(newDrawing: NewDrawing): CreateDrawingResult =
        tryCatching(
            action = {
                val ownerId = accountLocalDataSource.getAccount()?.public
                ownerId?.let {
                    val requestModel = newDrawing.toRequestModel(ownerId)
                    drawingRemoteDataSource.saveDrawing(requestModel)
                    CreateDrawingResult.Success
                } ?: CreateDrawingResult.UnknownFailure
            },
            error = { CreateDrawingResult.UnknownFailure }
        )

    override suspend fun getSelectedDrawingId(): SelectedDrawingIdResult =
        selectedDrawingIdLocalDataSource.getSelectedDrawingId()
            ?.let { SelectedDrawingIdResult.Success(it) }
            ?: SelectedDrawingIdResult.Empty

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun observeSelectedDrawing(): Flow<SelectedDrawingUpdate> =
        selectedDrawingIdLocalDataSource.observeSelectedDrawingId()
            .flatMapMerge { drawingId ->
                updateTickFlow
                    .map { drawingId }
                    .onStart { emit(drawingId) }
            }
            .map { getDrawingById(it) }
            .filterNotNull()
            .map { drawingResponseModel ->
                val participants = drawingResponseModel.participantsIds
                    .map { userRemoteDataSource.getUser(it).toDomain() }
                val drawnParticipant = drawingResponseModel.drawnParticipantId
                    ?.let { userRemoteDataSource.getUser(it).toDomain() }
                drawingResponseModel.toDomain(participants, drawnParticipant)
            }
            .map { SelectedDrawingUpdate.Success(it) }
            .catch { SelectedDrawingUpdate.UnknownFailure }

    override suspend fun addParticipant(
        drawingId: String,
        participantId: String
    ): AddParticipantResult {
        return tryCatching(
            action = {
                getDrawingById(drawingId)
                    ?.let { drawing ->
                        val drawingWithNewParticipant = drawing.copy(
                            participantsIds = drawing.participantsIds + participantId
                        ).toRequestModel()
                        drawingRemoteDataSource.saveDrawing(drawingWithNewParticipant)
                        updateTickFlow.emit(Unit)
                        AddParticipantResult.Success
                    } ?: AddParticipantResult.UnknownFailure
            },
            error = { AddParticipantResult.UnknownFailure }
        )
    }

    override suspend fun drawParticipants(drawingId: String): DrawParticipantsResult {
        return tryCatching(
            action = {
                drawingDrawRemoteDataSource.drawParticipants(drawingId)
                updateTickFlow.emit(Unit)
                DrawParticipantsResult.Success
            },
            error = { DrawParticipantsResult.UnknownFailure }
        )
    }

    override suspend fun closeLobby(drawingId: String): CloseDrawingLobbyResult {
        return tryCatching(
            action = {
                getDrawingById(drawingId)
                    ?.let { drawing ->
                        if (!drawing.isLobbyClosed) {
                            val drawingWithClosedLobby = drawing.copy(
                                isLobbyClosed = true
                            ).toRequestModel()
                            drawingRemoteDataSource.saveDrawing(drawingWithClosedLobby)
                            updateTickFlow.emit(Unit)
                            CloseDrawingLobbyResult.Success()
                        } else {
                            CloseDrawingLobbyResult.AlreadyClosed
                        }
                    } ?: CloseDrawingLobbyResult.UnknownFailure
            },
            error = { CloseDrawingLobbyResult.UnknownFailure }
        )
    }

    private suspend fun getDrawingById(drawingId: String) =
        drawingRemoteDataSource.getDrawings().firstOrNull { it.id == drawingId }
}

fun NewDrawing.toRequestModel(ownerId: String) = DrawingRequestModel(
    title = title,
    description = description,
    createdDate = Clock.System.now(),
    participantsIds = listOf(ownerId),
    isLobbyClosed = false,
    drawnParticipantId = null,
)

fun DrawingResponseModel.toRequestModel() = DrawingRequestModel(
    id = id,
    title = title,
    description = description,
    createdDate = Clock.System.now(),
    participantsIds = participantsIds,
    isLobbyClosed = isLobbyClosed,
    drawnParticipantId = drawnParticipantId,
)

fun DrawingResponseModel.toDomain(
    users: List<DrawingParticipant>,
    drawnParticipant: DrawingParticipant?
) = Drawing(
    id = id,
    title = title,
    description = description,
    createdDate = createdDate,
    participants = users,
    isLobbyClosed = isLobbyClosed,
    drawnParticipant = drawnParticipant,
)

fun UserResponseModel.toDomain() = DrawingParticipant(
    id = id,
    name = name,
    surname = surname,
)