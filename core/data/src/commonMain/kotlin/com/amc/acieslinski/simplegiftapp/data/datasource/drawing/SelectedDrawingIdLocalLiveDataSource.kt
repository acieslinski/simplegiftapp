package com.amc.acieslinski.simplegiftapp.data.datasource.drawing

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.filterNotNull

class SelectedDrawingIdLocalLiveDataSource: SelectedDrawingIdLocalDataSource {
    private val selectedDrawingId = MutableStateFlow<String?>(null)
    override suspend fun selectDrawingId(drawingId: String) {
        selectedDrawingId.value = drawingId
    }

    override suspend fun getSelectedDrawingId(): String? = selectedDrawingId.value

    override fun observeSelectedDrawingId(): Flow<String> = selectedDrawingId
        .filterNotNull()
}