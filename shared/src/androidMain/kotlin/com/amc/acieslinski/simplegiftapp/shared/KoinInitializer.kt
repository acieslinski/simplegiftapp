@file:Suppress("unused")

package com.amc.acieslinski.simplegiftapp.shared

import android.content.Context
import com.amc.acieslinski.simplegiftapp.drawing.di.accountModule
import com.amc.acieslinski.simplegiftapp.drawing.di.drawingModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

fun initKoin(context: Context) {
    val modules = accountModule + drawingModule

    startKoin {
        androidContext(context)
        modules(modules)
    }
}