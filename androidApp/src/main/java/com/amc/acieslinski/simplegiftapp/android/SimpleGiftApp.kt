package com.amc.acieslinski.simplegiftapp.android

import android.app.Application
import com.amc.acieslinski.simplegiftapp.shared.initKoin

class SimpleGiftApp : Application() {

    override fun onCreate() {
        super.onCreate()

        initKoin(this)
    }
}