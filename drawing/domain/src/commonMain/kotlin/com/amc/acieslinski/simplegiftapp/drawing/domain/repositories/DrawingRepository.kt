package com.amc.acieslinski.simplegiftapp.drawing.domain.repositories

import com.amc.acieslinski.simplegiftapp.drawing.domain.model.Drawing

interface DrawingRepository {
    fun createDrawing(drawing: Drawing)
}