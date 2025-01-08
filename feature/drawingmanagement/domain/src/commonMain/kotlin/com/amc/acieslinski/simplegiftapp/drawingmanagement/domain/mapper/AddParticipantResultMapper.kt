package com.amc.acieslinski.simplegiftapp.drawingmanagement.domain.mapper

import com.amc.acieslinski.simplegiftapp.drawingmanagement.domain.model.AddParticipantResult
import com.amc.acieslinski.simplegiftapp.drawingmanagement.domain.model.SaveDrawingResult
import com.amc.acieslinski.simplegiftapp.drawingmanagement.domain.model.SelectedDrawingResult

class AddParticipantResultMapper {
    fun map(result: SaveDrawingResult) = when (result) {
        is SaveDrawingResult.Success -> AddParticipantResult.Success
        is SaveDrawingResult.UnknownFailure -> AddParticipantResult.UnknownFailure
    }

    fun map(result: SelectedDrawingResult) = when (result) {
        is SelectedDrawingResult.Success -> AddParticipantResult.Success
        is SelectedDrawingResult.Empty -> AddParticipantResult.UnknownFailure
    }
}