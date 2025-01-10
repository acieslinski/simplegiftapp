package com.amc.acieslinski.simplegiftapp.dashboard.di

import com.amc.acieslinski.simplegiftapp.dashboard.presentation.DashboardViewModel
import com.amc.acieslinski.simplegiftapp.dashboard.presentation.DashboardViewModelImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

@Suppress("USELESS_CAST")
actual val platformDashboardUiModule = module {
    // ui
    viewModel { DashboardViewModelImpl(get(), get()) as DashboardViewModel }
}