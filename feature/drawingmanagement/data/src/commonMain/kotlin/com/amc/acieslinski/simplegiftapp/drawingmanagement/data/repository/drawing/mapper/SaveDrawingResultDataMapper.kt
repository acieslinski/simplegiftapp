package com.amc.acieslinski.simplegiftapp.drawingmanagement.data.repository.drawing.mapper

import com.amc.acieslinski.simplegiftapp.data.datasource.exception.RequestException
import com.amc.acieslinski.simplegiftapp.drawingmanagement.domain.model.SaveDrawingResult

class SaveDrawingResultDataMapper {
    fun map(result: Result<Unit>): SaveDrawingResult = when {
        result.isSuccess -> SaveDrawingResult.Success
        else -> {
            val exception = result.exceptionOrNull()
            when {
                exception is RequestException && exception.statusCode == 409 -> SaveDrawingResult.DrawingClosedFailure
                else -> SaveDrawingResult.UnknownFailure
            }
        }
    }

}