package com.amc.acieslinski.simplegiftapp.dashboard.di

import com.amc.acieslinski.simplegiftapp.dashboard.presentation.DashboardViewModel
import com.amc.acieslinski.simplegiftapp.dashboard.presentation.DashboardViewModelImpl
import com.amc.acieslinski.simplegiftapp.dashboard.presentation.mapper.DrawingMapper
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

@Suppress("USELESS_CAST")
actual val platformDashboardUiModule = module {
    // ui
    single<DrawingMapper> { DrawingMapper }
    viewModel { DashboardViewModelImpl(get(), get(), get()) as DashboardViewModel }
}