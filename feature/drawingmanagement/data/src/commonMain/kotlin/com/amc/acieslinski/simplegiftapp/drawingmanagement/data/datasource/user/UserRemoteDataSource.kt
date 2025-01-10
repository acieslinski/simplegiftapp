package com.amc.acieslinski.simplegiftapp.drawingmanagement.data.datasource.user

import com.amc.acieslinski.simplegiftapp.data.datasource.exception.RequestException
import com.amc.acieslinski.simplegiftapp.drawingmanagement.data.datasource.user.model.UserResponseModel
import kotlin.coroutines.cancellation.CancellationException

interface UserRemoteDataSource {
    @Throws(RequestException::class, CancellationException::class)
    suspend fun getUser(userId: String): UserResponseModel
}