@file:Suppress("unused")

package com.amc.acieslinski.simplegiftapp.shared

import com.amc.acieslinski.simplegiftapp.account.presentation.RegistrationViewModel
import com.amc.acieslinski.simplegiftapp.drawing.di.accountModule
import com.amc.acieslinski.simplegiftapp.drawing.di.drawingModule
import com.amc.acieslinski.simplegiftapp.drawing.presentation.DrawingViewModel
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.context.startKoin

fun initKoin() {
    startKoin {
        modules(drawingModule + accountModule)
    }
}

class DrawingInjector : KoinComponent {
    val drawingViewModel: DrawingViewModel by inject()
}

class RegistrationInjector : KoinComponent {
    val registrationViewModel: RegistrationViewModel by inject()
}