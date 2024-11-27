package com.amc.acieslinski.simplegiftapp.drawingmanagement.di

import com.amc.acieslinski.simplegiftapp.drawingmanagement.presentation.DrawingViewModel
import com.amc.acieslinski.simplegiftapp.drawingmanagement.presentation.DrawingViewModelImpl
import org.koin.dsl.module


actual val platformDrawingUiModule = module {
    single<DrawingViewModel> { DrawingViewModelImpl(get()) }
}