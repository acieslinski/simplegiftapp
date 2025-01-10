package com.amc.acieslinski.simplegiftapp.drawingmanagement.presentation

import kotlinx.coroutines.flow.MutableStateFlow

class ParticipantQrScannerFakeViewModel : ParticipantQrScannerViewModel() {
    override val participantQrScannerUiState = MutableStateFlow(
        ParticipantQrScannerUiState()
    )

    override fun onAddParticipantAction(userToken: String) {
        TODO("Not yet implemented")
    }

    override fun onAlertAckAction() {
        TODO("Not yet implemented")
    }
}