package com.amc.acieslinski.simplegiftapp.dashboard.data.datasource.qrcode

interface QrCodeLocalDataSource {
    suspend fun generateQrCode(input: String): ByteArray
}