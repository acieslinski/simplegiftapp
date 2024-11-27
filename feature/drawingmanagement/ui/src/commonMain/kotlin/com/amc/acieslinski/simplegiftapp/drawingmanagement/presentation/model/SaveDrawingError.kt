package com.amc.acieslinski.simplegiftapp.drawingmanagement.presentation.model

sealed interface SaveDrawingError {
    data object Unknown: SaveDrawingError
}