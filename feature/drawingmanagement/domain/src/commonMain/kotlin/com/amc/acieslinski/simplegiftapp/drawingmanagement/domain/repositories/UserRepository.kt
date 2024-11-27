package com.amc.acieslinski.simplegiftapp.drawingmanagement.domain.repositories

import com.amc.acieslinski.simplegiftapp.drawingmanagement.domain.model.UserResult
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun getUser(idToken: String): Flow<UserResult>
}

