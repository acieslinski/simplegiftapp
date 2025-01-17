package com.amc.acieslinski.simplegiftapp.dashboard.domain

import com.amc.acieslinski.simplegiftapp.dashboard.domain.model.PublicQrCodeResult
import com.amc.acieslinski.simplegiftapp.dashboard.domain.model.UserPublicIdResult
import com.amc.acieslinski.simplegiftapp.dashboard.domain.repositories.UserRepository

class GetCurrentUserPublicQrCode(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(): PublicQrCodeResult {
        return when (val userPublicIdResult = userRepository.getCurrentUserPublicId()) {
            is UserPublicIdResult.Success -> userRepository.getQrCode(userPublicIdResult.userId)
            is UserPublicIdResult.Failure -> PublicQrCodeResult.UnknownFailure
        }
    }
}