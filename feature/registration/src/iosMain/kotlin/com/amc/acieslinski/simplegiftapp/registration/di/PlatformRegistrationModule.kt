package com.amc.acieslinski.simplegiftapp.registration.di

import com.amc.acieslinski.simplegiftapp.registration.presentation.RegistrationViewModel
import org.koin.dsl.module

actual val platformRegistrationUiModule = module {
    single<RegistrationViewModel> { RegistrationViewModel(get(), get()) }
}