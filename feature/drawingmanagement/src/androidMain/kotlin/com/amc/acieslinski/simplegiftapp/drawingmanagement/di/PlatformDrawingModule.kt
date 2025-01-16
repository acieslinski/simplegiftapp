package com.amc.acieslinski.simplegiftapp.drawingmanagement.di

import com.amc.acieslinski.simplegiftapp.drawingmanagement.presentation.DrawingViewModel
import com.amc.acieslinski.simplegiftapp.drawingmanagement.presentation.DrawingLiveViewModel
import com.amc.acieslinski.simplegiftapp.drawingmanagement.presentation.NewDrawingViewModel
import com.amc.acieslinski.simplegiftapp.drawingmanagement.presentation.NewDrawingLiveViewModel
import com.amc.acieslinski.simplegiftapp.drawingmanagement.presentation.ParticipantQrScannerLiveViewModel
import com.amc.acieslinski.simplegiftapp.drawingmanagement.presentation.ParticipantQrScannerViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

@Suppress("USELESS_CAST")
actual val platformDrawingUiModule = module {
    // ui
    viewModel { DrawingLiveViewModel(get(), get()) as DrawingViewModel }
    viewModel { ParticipantQrScannerLiveViewModel(get(), get()) as ParticipantQrScannerViewModel }
    viewModel { NewDrawingLiveViewModel(get()) as NewDrawingViewModel }
}