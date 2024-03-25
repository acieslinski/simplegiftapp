package com.amc.acieslinski.simplegiftapp.android.ui.navigation

interface ScannerNav {
    fun startScannerScreen(callback: (String) -> Unit)

    companion object {
        val DUMB = object : ScannerNav {
            override fun startScannerScreen(callback: (String) -> Unit) {
                // do nothing - empty implementation
            }
        }
    }
}