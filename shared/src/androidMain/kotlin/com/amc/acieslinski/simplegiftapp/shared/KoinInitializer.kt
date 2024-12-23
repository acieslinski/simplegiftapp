@file:Suppress("unused")

package com.amc.acieslinski.simplegiftapp.shared

import android.content.Context
import com.amc.acieslinski.simplegiftapp.dashboard.di.dashboardModule
import com.amc.acieslinski.simplegiftapp.drawingmanagement.di.drawingManagementModule
import com.amc.acieslinski.simplegiftapp.registration.di.registrationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

fun initKoin(context: Context) {
    val modules = registrationModule + drawingManagementModule + dashboardModule

    startKoin {
        androidContext(context)
        modules(modules)
    }
}