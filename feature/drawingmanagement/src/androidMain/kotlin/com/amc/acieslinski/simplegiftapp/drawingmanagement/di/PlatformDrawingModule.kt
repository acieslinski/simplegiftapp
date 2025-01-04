package com.amc.acieslinski.simplegiftapp.drawingmanagement.di

import com.amc.acieslinski.simplegiftapp.drawingmanagement.presentation.DrawingViewModel
import com.amc.acieslinski.simplegiftapp.drawingmanagement.presentation.DrawingViewModelImpl
import com.amc.acieslinski.simplegiftapp.drawingmanagement.presentation.NewDrawingViewModel
import com.amc.acieslinski.simplegiftapp.drawingmanagement.presentation.NewDrawingViewModelImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

@Suppress("USELESS_CAST")
actual val platformDrawingUiModule = module {
    // ui
    viewModel { DrawingViewModelImpl(get(), get()) as DrawingViewModel }
    viewModel { NewDrawingViewModelImpl(get()) as NewDrawingViewModel }
}