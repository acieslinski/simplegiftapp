package com.amc.acieslinski.simplegiftapp.dashboard.domain.repositories

import com.amc.acieslinski.simplegiftapp.dashboard.domain.model.PublicQrCodeResult
import com.amc.acieslinski.simplegiftapp.dashboard.domain.model.UserPublicIdResult

interface UserRepository {

    suspend fun getCurrentUserPublicId(): UserPublicIdResult

    suspend fun getQrCode(userId: String): PublicQrCodeResult
}