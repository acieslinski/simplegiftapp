package com.amc.acieslinski.simplegiftapp.drawingmanagement.data.repository.drawing

import com.amc.acieslinski.simplegiftapp.drawingmanagement.domain.model.AddParticipantResult
import com.amc.acieslinski.simplegiftapp.drawingmanagement.domain.model.CloseDrawingLobbyResult
import com.amc.acieslinski.simplegiftapp.drawingmanagement.domain.model.CreateDrawingResult
import com.amc.acieslinski.simplegiftapp.drawingmanagement.domain.model.DrawParticipantsResult
import com.amc.acieslinski.simplegiftapp.drawingmanagement.domain.model.Drawing
import com.amc.acieslinski.simplegiftapp.drawingmanagement.domain.model.DrawingParticipant
import com.amc.acieslinski.simplegiftapp.drawingmanagement.domain.model.NewDrawing
import com.amc.acieslinski.simplegiftapp.drawingmanagement.domain.model.SelectedDrawingIdResult
import com.amc.acieslinski.simplegiftapp.drawingmanagement.domain.model.SelectedDrawingUpdate
import com.amc.acieslinski.simplegiftapp.drawingmanagement.domain.repositories.DrawingRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.datetime.Clock
import kotlin.time.Duration.Companion.seconds

class DrawingFakeRepository : DrawingRepository {
    private var selectedDrawing = Drawing(
        id = "test-id",
        title = "Test drawing",
        description = "Test drawing of participants pairs",
        createdDate = Clock.System.now(),
        participants = emptyList(),
        drawnParticipant = null,
    )

    override suspend fun createDrawing(newDrawing: NewDrawing): CreateDrawingResult {
        delay(2.seconds)
        return CreateDrawingResult.Success
    }

    override suspend fun getSelectedDrawingId(): SelectedDrawingIdResult {
        return SelectedDrawingIdResult.Success(selectedDrawing.id)
    }

    override fun observeSelectedDrawing(): Flow<SelectedDrawingUpdate> = flowOf(
        SelectedDrawingUpdate.Success(
            drawing = selectedDrawing
        )
    )

    override suspend fun addParticipant(
        drawingId: String,
        participantId: String
    ): AddParticipantResult {
        delay(2.seconds)
        selectedDrawing = selectedDrawing.copy(
            participants = selectedDrawing.participants + DrawingParticipant(
                id = participantId,
                name = "testname",
                surname = "testsurname",
            )
        )
        return AddParticipantResult.Success
    }

    override suspend fun drawParticipants(drawingId: String): DrawParticipantsResult {
        selectedDrawing = selectedDrawing.copy(drawnParticipant = DrawingParticipant(
            id = "mock-id",
            name = "testname",
            surname = "testsurname",
        ))
        return DrawParticipantsResult.Success
    }

    override suspend fun closeLobby(drawingId: String): CloseDrawingLobbyResult {
        return if (!selectedDrawing.isLobbyClosed) {
            selectedDrawing = selectedDrawing.copy(isLobbyClosed = true)
            CloseDrawingLobbyResult.Success()
        } else {
            CloseDrawingLobbyResult.AlreadyClosed
        }
    }
}