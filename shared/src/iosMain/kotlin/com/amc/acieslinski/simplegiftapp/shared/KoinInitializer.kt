@file:Suppress("unused")

package com.amc.acieslinski.simplegiftapp.shared

import com.amc.acieslinski.simplegiftapp.drawingmanagement.di.drawingManagementModule
import com.amc.acieslinski.simplegiftapp.registration.di.registrationModule
import com.amc.acieslinski.simplegiftapp.drawingmanagement.presentation.DrawingViewModel
import com.amc.acieslinski.simplegiftapp.registration.presentation.RegistrationViewModel
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.context.startKoin

fun initKoin() {
    startKoin {
        modules(registrationModule + drawingManagementModule)
    }
}

class DrawingInjector : KoinComponent {
    val drawingViewModel: DrawingViewModel by inject()
}

class RegistrationInjector : KoinComponent {
    val registrationViewModel: RegistrationViewModel by inject()
}