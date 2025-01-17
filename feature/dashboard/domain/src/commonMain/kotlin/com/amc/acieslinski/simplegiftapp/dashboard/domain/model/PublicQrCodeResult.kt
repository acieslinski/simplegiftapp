package com.amc.acieslinski.simplegiftapp.dashboard.domain.model

import kotlin.jvm.Transient

sealed interface PublicQrCodeResult {
    @Suppress("ArrayInDataClass")
    data class Success(
        val publicId: String,
        @Transient val qrCodeBytes: ByteArray,
    ): PublicQrCodeResult

    sealed interface Failure: PublicQrCodeResult

    data object UnknownFailure: Failure
}