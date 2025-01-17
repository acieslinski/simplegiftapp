package com.amc.acieslinski.simplegiftapp.dashboard.data.datasource.qrcode

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import qrcode.QRCode
import qrcode.color.Colors

class QrCodeLocalLiveDataSource: QrCodeLocalDataSource {
    override suspend fun generateQrCode(input: String): ByteArray {
        return withContext(Dispatchers.Default) {
            val qrCodeEngine = QRCode.ofSquares()
                .withColor(Colors.BLACK)
                .withInnerSpacing(25)
                .withSize(25)
                .build(input)

            val pngBytes = qrCodeEngine.render()
            pngBytes.getBytes()
        }
    }

}