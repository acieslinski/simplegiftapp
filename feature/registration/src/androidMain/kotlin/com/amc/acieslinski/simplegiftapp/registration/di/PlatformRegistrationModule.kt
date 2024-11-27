package com.amc.acieslinski.simplegiftapp.registration.di

import com.amc.acieslinski.simplegiftapp.registration.presentation.RegistrationViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

actual val platformRegistrationUiModule = module {
    viewModel { RegistrationViewModel(get(), get()) }
}