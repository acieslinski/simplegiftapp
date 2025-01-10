package com.amc.acieslinski.simplegiftapp.drawingmanagement.data.repository.drawing

import com.amc.acieslinski.simplegiftapp.data.datasource.drawing.DrawingRemoteDataSource
import com.amc.acieslinski.simplegiftapp.data.datasource.drawing.SelectedDrawingIdLocalDataSource
import com.amc.acieslinski.simplegiftapp.data.datasource.drawing.model.DrawingRequestModel
import com.amc.acieslinski.simplegiftapp.data.datasource.drawing.model.DrawingResponseModel
import com.amc.acieslinski.simplegiftapp.drawingmanagement.domain.model.NewDrawing
import com.amc.acieslinski.simplegiftapp.drawingmanagement.domain.repositories.DrawingRepository
import com.amc.acieslinski.simplegiftapp.data.repository.Repository
import com.amc.acieslinski.simplegiftapp.drawingmanagement.data.datasource.user.UserRemoteDataSource
import com.amc.acieslinski.simplegiftapp.drawingmanagement.data.datasource.user.model.UserResponseModel
import com.amc.acieslinski.simplegiftapp.drawingmanagement.domain.model.AddParticipantResult
import com.amc.acieslinski.simplegiftapp.drawingmanagement.domain.model.CreateDrawingResult
import com.amc.acieslinski.simplegiftapp.drawingmanagement.domain.model.Drawing
import com.amc.acieslinski.simplegiftapp.drawingmanagement.domain.model.SelectedDrawingUpdate
import com.amc.acieslinski.simplegiftapp.drawingmanagement.domain.model.DrawingParticipant
import com.amc.acieslinski.simplegiftapp.drawingmanagement.domain.model.SelectedDrawingIdResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.datetime.Clock

class DrawingLiveRepository(
    private val drawingRemoteDataSource: DrawingRemoteDataSource,
    private val selectedDrawingIdLocalDataSource: SelectedDrawingIdLocalDataSource,
    private val userRemoteDataSource: UserRemoteDataSource,
) : DrawingRepository, Repository() {

    override suspend fun createDrawing(newDrawing: NewDrawing): CreateDrawingResult =
        tryCatching(
            action = {
                val requestModel = newDrawing.toRequestModel()
                drawingRemoteDataSource.saveDrawing(requestModel)
                CreateDrawingResult.Success
            },
            error = { CreateDrawingResult.UnknownFailure }
        )

    override suspend fun getSelectedDrawingId(): SelectedDrawingIdResult =
        selectedDrawingIdLocalDataSource.getSelectedDrawingId()
            ?.let { SelectedDrawingIdResult.Success(it) }
            ?: SelectedDrawingIdResult.Empty

    override fun observeSelectedDrawing(): Flow<SelectedDrawingUpdate> =
        selectedDrawingIdLocalDataSource.observeSelectedDrawingId()
            .map { getDrawingById(it) }
            .filterNotNull()
            .map { drawingResponseModel ->
                drawingResponseModel.participantsIds
                    .map { userRemoteDataSource.getUser(it).toDomain() }
                    .let { drawingResponseModel.toDomain(it) }
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
                        AddParticipantResult.Success
                    } ?: AddParticipantResult.UnknownFailure
            },
            error = { AddParticipantResult.UnknownFailure }
        )
    }

    private suspend fun getDrawingById(drawingId: String) =
        drawingRemoteDataSource.getDrawings().firstOrNull { it.id == drawingId }
}

fun NewDrawing.toRequestModel() = DrawingRequestModel(
    title = title,
    description = description,
    createdDate = Clock.System.now(),
    participantsTokens = emptyList(),
)

fun DrawingResponseModel.toRequestModel() = DrawingRequestModel(
    id = id,
    title = title,
    description = description,
    createdDate = Clock.System.now(),
    participantsTokens = participantsIds,
)

fun DrawingResponseModel.toDomain(users: List<DrawingParticipant>) = Drawing(
    id = id,
    title = title,
    description = description,
    createdDate = createdDate,
    participants = users,
)

fun UserResponseModel.toDomain() = DrawingParticipant(
    id = id,
    name = name,
    surname = surname,
)