package com.amc.acieslinski.simplegiftapp.dashboard.di

import com.amc.acieslinski.simplegiftapp.dashboard.presentation.DashboardViewModel
import com.amc.acieslinski.simplegiftapp.dashboard.presentation.DashboardViewModelImpl
import com.amc.acieslinski.simplegiftapp.dashboard.presentation.mapper.DrawingMapper
import org.koin.dsl.module


actual val platformDashboardUiModule = module {
    single<DrawingMapper> { DrawingMapper }
    single<DashboardViewModel> { DashboardViewModelImpl(get(), get(), get()) }
}