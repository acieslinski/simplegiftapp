package com.amc.acieslinski.simplegiftapp.drawingmanagement.domain.repositories

import com.amc.acieslinski.simplegiftapp.drawingmanagement.domain.model.AddParticipantResult
import com.amc.acieslinski.simplegiftapp.drawingmanagement.domain.model.CreateDrawingResult
import com.amc.acieslinski.simplegiftapp.drawingmanagement.domain.model.DrawParticipantsResult
import com.amc.acieslinski.simplegiftapp.drawingmanagement.domain.model.NewDrawing
import com.amc.acieslinski.simplegiftapp.drawingmanagement.domain.model.CloseDrawingLobbyResult
import com.amc.acieslinski.simplegiftapp.drawingmanagement.domain.model.SelectedDrawingIdResult
import com.amc.acieslinski.simplegiftapp.drawingmanagement.domain.model.SelectedDrawingUpdate
import kotlinx.coroutines.flow.Flow

interface DrawingRepository {
    suspend fun createDrawing(newDrawing: NewDrawing): CreateDrawingResult

    suspend fun getSelectedDrawingId(): SelectedDrawingIdResult

    fun observeSelectedDrawing(): Flow<SelectedDrawingUpdate>

    suspend fun addParticipant(drawingId: String, participantId: String): AddParticipantResult

    suspend fun drawParticipants(drawingId: String): DrawParticipantsResult

    suspend fun closeLobby(drawingId: String): CloseDrawingLobbyResult
}