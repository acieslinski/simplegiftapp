package com.amc.acieslinski.simplegiftapp.dashboard.data.repository.user

import com.amc.acieslinski.simplegiftapp.dashboard.data.datasource.qrcode.QrCodeLocalDataSource
import com.amc.acieslinski.simplegiftapp.dashboard.domain.model.PublicQrCodeResult
import com.amc.acieslinski.simplegiftapp.dashboard.domain.model.UserPublicIdResult
import com.amc.acieslinski.simplegiftapp.dashboard.domain.repositories.UserRepository
import com.amc.acieslinski.simplegiftapp.data.datasource.account.AccountLocalDataSource
import com.amc.acieslinski.simplegiftapp.data.repository.Repository

class UserLiveRepository(
    private val accountLocalDataSource: AccountLocalDataSource,
    private val qrCodeLocalDataSource: QrCodeLocalDataSource,
): UserRepository, Repository() {
    override suspend fun getCurrentUserPublicId(): UserPublicIdResult {
        return tryCatching(
            action = {
                val userPublicId = accountLocalDataSource.getAccount()?.public
                if (userPublicId != null) {
                    UserPublicIdResult.Success(userPublicId)
                } else {
                    UserPublicIdResult.UnknownFailure
                }
            },
            error = { UserPublicIdResult.UnknownFailure }
        )
    }

    override suspend fun getQrCode(userPublicId: String): PublicQrCodeResult {
        return tryCatching(
            action = {
                val userPublicQrCode = qrCodeLocalDataSource.generateQrCode(userPublicId)
                PublicQrCodeResult.Success(userPublicId, userPublicQrCode)
            },
            error = { PublicQrCodeResult.UnknownFailure }
        )
    }

}